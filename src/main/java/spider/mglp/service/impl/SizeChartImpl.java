package spider.mglp.service.impl;

import com.csvreader.CsvWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spider.mglp.enums.UrlEnum;
import etl.ReadThisTimeSpuCodeFile;
import spider.mglp.util.SqlUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/15 下午9:54</pre>
 */
public class SizeChartImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(SizeChartImpl.class);

    /**
     * @param localDate 字符串日期
     * @param type      尺码还是试穿
     * @param rules     对应的规则可能有哪些
     */
    public void downloadSizeOrTryChart(String localDate, String type, String[] rules) throws IOException {
        // 1、查询数据库（手动录入的库），获取itemId——链接 数据
        Map<String, String> itemIdLinkMap = SqlUtils.getItemIdAndImgsUrl();
        // 2、读取磁盘文件，今日所有在架状态的spu
        Map<String, String> spuIDMap = ReadThisTimeSpuCodeFile.readSpuEveryday(localDate);
        // 3、读取截至前一天已经下载的spu集合
        Set<String> yesterday;
        // 爬取的数据放置的日期目录
        String sizeParentFilePath = UrlEnum.CSV_SIZE_SUCCESS.getDesc() + localDate;
        String tryParentFilePath = UrlEnum.CSV_TRY_SUCCESS.getDesc() + localDate;
        // 本次主要想爬取的类型（size\try）的存储路径
        String todayDownloaded;
        if (type.equals("size")) {
            yesterday = ReadThisTimeSpuCodeFile.readSpuFile(UrlEnum.SPU_SIZE_SUCCESS.getDesc(), "other");
            todayDownloaded = sizeParentFilePath;
        } else {
            yesterday = ReadThisTimeSpuCodeFile.readSpuFile(UrlEnum.SPU_TRY_SUCCESS.getDesc(), "other");
            todayDownloaded = tryParentFilePath;
        }
        // 目录只创建一次
        mkDir(sizeParentFilePath);
        mkDir(tryParentFilePath);
        // 删除已经爬取过的spu，前一天的历史记录，删除一次
        deleteSpidered(spuIDMap, yesterday);
        // 控制手动添加入库的文件只在第一次循环时写入
        boolean writeByHandFlag = true;
        FileWriter fileWriter = new FileWriter(new File(UrlEnum.SPU_NEED_ADD_BYHAND.getDesc()));
        for (String rule : rules) {
            // 读取截至本次循环，已经下载的，不需要再次下载了
            Set<String> spidered = ReadThisTimeSpuCodeFile.countSpuFileLocal(todayDownloaded);
            // 删除之前循环下载了的，避免重复下载
            if (spidered != null) {
                deleteSpidered(spuIDMap, spidered);
            }
            // go\hand查看还有多少是没有手动添加的
            int go = 0, hand = 0;
            Set<String> handSet = new HashSet<>(64);
            for (Map.Entry<String, String> entry : spuIDMap.entrySet()) {
                // 从taobao_link获得itemId————————https://item.taobao.com/item.htm?id=568944784760&amp;amp;spm=2014.12440355.0.0
                int begin = entry.getValue().lastIndexOf("id=") + 3;
                String itemId = entry.getValue().substring(begin, begin + 12);
                // spuCode用于待会的csv文件名
                String spuCode = entry.getKey();
                // 根据该itemId，查询itemIdLinkMap（我们手动录入的数据）中的value值
                if (itemIdLinkMap.containsKey(itemId)) {
                    go++;
                    System.out.println("go spuCode, " + spuCode + "\t=\t" + itemIdLinkMap.get(itemId));
                    String url = itemIdLinkMap.get(itemId);
                    // 获取数据中elements
                    Elements elements = spiderFromUrl(url, rule);
                    // 解析数据并写磁盘，csv
                    parseData(elements, spuCode, sizeParentFilePath, url, tryParentFilePath);
                } else if (writeByHandFlag) {
                    if (!handSet.contains(itemId)) {
                        hand++;
                        handSet.add(itemId);
                        fileWriter.write(itemId + "," + entry.getValue());
                        fileWriter.write("\n");
                    }
                }
            }
            // 保证只写一次（hand也只加一轮，所以后面的规则中打印hand  :0）
            writeByHandFlag = false;
            System.out.println("go  :" + go + " , hand  :" + hand);
        }
        fileWriter.close();
    }

    /**
     * 删除已经爬取过的spu
     *
     * @param spuIDMap ,
     * @param spidered ,
     */
    public void deleteSpidered(Map<String, String> spuIDMap, Set<String> spidered) {
        for (Iterator<Map.Entry<String, String>> it = spuIDMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = it.next();
            String spuCode = (String) entry.getKey();
            if (spidered.contains(spuCode)) {
                it.remove();
                System.out.println("已经下载过，remove spu: " + spuCode);
            }
        }
    }

    /**
     * 创建一次目录
     *
     * @param dirPath 目录
     */
    public void mkDir(String dirPath) {
        File file = new File(dirPath);
        // 如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            if (!file.mkdir()) {
                LOGGER.error("创建目录  {}  失败", dirPath);
            }
        }
    }

    /**
     * 拿相关数据
     *
     * @param url  数据url
     * @param rule 本次获取数据的获取标签规则
     * @return Elements
     */
    public Elements spiderFromUrl(String url, String rule) {
        Document doc;
        InputStream is = null;
        try {
            is = new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String html = "";
        try {
            assert is != null;
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "GBK"));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            html = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc = Jsoup.parse(html);
        System.out.println("========开始rule:  " + rule);
        return doc.select(rule);
    }

    /**
     * 解析获取的数据
     *
     * @param elements
     * @param spuCode
     * @param sizeParentPath
     * @param url
     * @param tryParentPath
     * @return
     */
    public int parseData(Elements elements, String spuCode, String sizeParentPath, String url, String tryParentPath) {
        if (elements.size() > 0) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@:" + elements.size() + "  =    " + url);
            for (int i = 0; i < elements.size(); i++) {
                // 选择到元素
                Element last = elements.get(i);
                // 选择到table
                Elements trs = last.select("table").select("tr");
                int column = trs.size();
                if (column == 0) {
                    System.out.println("下一个，本次无tr");
                    continue;
                }
                System.out.println(column + "    ..........");
                String filePathAndName;
                if (column < 6 || column > 11) {
                    filePathAndName = sizeParentPath + "/" + spuCode + "_size_" + column + "_" + i + ".csv";
                    writeCsvFile(column, filePathAndName, trs);
                } else {
                    filePathAndName = tryParentPath + "/" + spuCode + "_try_" + column + "_" + i + ".csv";
                    writeCsvFile(column, filePathAndName, trs);
                }
            }
        } else {
            LOGGER.warn("{}\t{}", spuCode, url);
        }
        return 0;
    }

    public void writeCsvFile(int column, String filePath, Elements trs) {
        // 创建CSV写对象
        CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
        // 获取tr
        for (int i = 0; i < column; i++) {
            System.out.println("这是第 +++++++++  " + (i + 1) + "行数据。");
            //获取每一行的列
            Elements tds = trs.get(i).select("td");
            String[] values = new String[tds.size()];
            for (int j = 0; j < tds.size(); j++) {
                //获取第i行第j列的值
                String val = tds.get(j).text();
                // 多尺码，比如M\\s,所以用的制表符隔开
                values[j] = val.replaceAll("\\\\", " ").trim();
            }
            try {
                csvWriter.writeRecord(values);
                csvWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        csvWriter.close();
    }
}
