package spider.mglp.service.impl;

import com.csvreader.CsvWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spider.mglp.enums.UrlEnum;
import spider.mglp.pojo.SizeChart;
import spider.mglp.util.ReadThisTimeSpuCodeFile;
import spider.mglp.util.SqlUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <p>pakage: spider.mglp.service.impl,descirption:</p>
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/15 下午9:54</pre>
 */
public class SizeChartImpl implements spider.mglp.service.SizeChart {
    private static final Logger LOGGER = LoggerFactory.getLogger(SizeChartImpl.class);

    @Override
    public void downloadSizeChart() {
        // 查询数据库，获取itemId——链接 数据
        HashMap<String, String> itemIdLinkMap = SqlUtils.getItemIdAndImgsUrl();
        // 查询数据库，获取所有的spu_code和taobao_link
        HashMap<String, String> spuIDMap = SqlUtils.getSpuCodeAndTbLink();
        Set<String> spuSet = ReadThisTimeSpuCodeFile.readSpuFile("/Users/Shared/size_chart/spildered.txt");
        // 删除已经爬取了的code
        for (Iterator<Map.Entry<String, String>> it = spuIDMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = it.next();
            String spuCode = (String) entry.getKey();
            if (spuSet.contains(spuCode)) {
                it.remove();
            }
        }
        System.out.println("还未下载的 spu size:  " + spuIDMap.size());

        // 继续爬取更新
        File spidered = new File(UrlEnum.BASIC_OUTFILE_PATH.getDesc() + "spildered.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(spidered, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, String> entry : spuIDMap.entrySet()) {
            // 从taobao_link获得itemId————————https://item.taobao.com/item.htm?id=568944784760&amp;amp;spm=2014.12440355.0.0
            int begin = entry.getValue().lastIndexOf("id=") + 3;
            String itemId = entry.getValue().substring(begin, begin + 12);
            // spuCode用于待会的csv文件名
            String spuCode = entry.getKey();
            // 根据该itemId，查询itemIdLinkMap中的value值
            if (itemIdLinkMap.containsKey(itemId)) {
                SizeChart sizeChart = new SizeChart();
                sizeChart.setSpuCode(spuCode);
                System.out.println("go itemId, " + itemId + "\t=\t" + itemIdLinkMap.get(itemId));
                String url = itemIdLinkMap.get(itemId);
                // 拿数据去
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
                // 尺码表和试穿表,第一波，53，很多只有size
//                Elements elementsSize = doc.select("div.sizeTable");
//                Elements elementsTry = doc.select("div.tryTable");
                // 第二波，解决更多的试穿
                Elements elementsTry = doc.select("div.screenshot.section.png");
                Elements elementsTry2 = doc.select("div.screenshot.png.section");
                tryOrSizeChart_1(elementsTry2, spuCode, url, "try", fileOutputStream);
                tryOrSizeChart_1(elementsTry, spuCode, url, "try", fileOutputStream);
            }
        }
        try {
            assert fileOutputStream != null;
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 模板一
     *
     * @param elements
     * @param spuCode
     * @param url
     * @param status
     */
    public int tryOrSizeChart_1(Elements elements, String spuCode, String url, String status, FileOutputStream fileOutputStream) {
        if (elements.size() > 0) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@" + elements.size() + url);
            // 选择到试穿div
            Element last = elements.last();
            // 选择到table
            Elements trs = last.select("table").select("tr");
            int column = trs.size();
            if (column == 0) {
                return -1;
            }
            String filePathAndName = UrlEnum.BASIC_OUTFILE_PATH.getDesc() + spuCode + "_" + status + "_" + column + ".csv";
            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(filePathAndName, ',', Charset.forName("UTF-8"));
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
                    values[j] = val.replaceAll("\\\\", "\t").trim();
                }
                try {
                    csvWriter.writeRecord(values);
                    csvWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            csvWriter.close();
            try {
                String s = spuCode + "\r\n";
                fileOutputStream.write(s.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            LOGGER.warn("{}\t{}\t{}", spuCode, url, status);
        }
        return 0;
    }

    public static void main(String[] args) {
        SizeChartImpl sizeChart = new SizeChartImpl();
        sizeChart.downloadSizeChart();
    }
}
