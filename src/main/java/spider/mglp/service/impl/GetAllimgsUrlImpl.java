package spider.mglp.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spider.mglp.enums.SqlEnum;
import spider.mglp.enums.UrlEnum;
import spider.mglp.pojo.ProductSpuWithBLOBs;
import spider.mglp.service.GetAllImgsUrl;
import etl.ReadThisTimeSpuCodeFile;
import spider.mglp.util.SpiderHeader;
import spider.mglp.util.SqlUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>pakage: spider.mglp.service.impl,descirption:</p>
 *
 * 优先级：2
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/14 上午9:25</pre>
 */
public class GetAllimgsUrlImpl implements GetAllImgsUrl {
    private int size = 1;
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAllimgsUrlImpl.class);

    @Override
    public void getAllImgsUrlFromApi(String localDate) {
        // 查询数据库（手动录入的库），获取itemId——链接 数据
        HashMap<String, String> itemIdLinkMap = SqlUtils.getItemIdAndImgsUrl();
        // 读取磁盘文件，今日所有在架状态的spu
        String todaySpu = UrlEnum.SPU_EVERYDAY_PATH.getDesc() + "spu_" + localDate + ".txt";
        HashMap<String, String> spuIDMap = SqlUtils.getSpuCodeAndTbLink();
        // 在今天看来，还没有爬取的图像
        spuIDMap = GetAllimgsUrlImpl.filtrationSpuCode(spuIDMap, "/Users/wanghai/Desktop/spucode_remain_20180720.txt");

        System.out.println(spuIDMap.size());
        ArrayList<ProductSpuWithBLOBs> records = new ArrayList<>(256);
        for (Map.Entry<String, String> entry : spuIDMap.entrySet()) {
            // 从taobao_link获得itemId————————https ://item.taobao.com/item.htm?id=568944784760&amp;amp;spm=2014.12440355.0.0
            int begin = entry.getValue().lastIndexOf("id=") + 3;
            String itemId = entry.getValue().substring(begin, begin + 12);
            /*
            // 打印所有共用图片的spu_code
            if (idSet.contains(itemId)) {
                System.out.println(entry.getKey());
            } else idSet.add(itemId);
            */

            // spuCode用于待会插入数据库
            String spuCode = entry.getKey();
            // 根据该itemId，查询itemIdLinkMap中的value值
            if (itemIdLinkMap.containsKey(itemId)) {
                size++;
                ProductSpuWithBLOBs productSpu = new ProductSpuWithBLOBs();
                productSpu.setSpuCode(spuCode);
                System.out.println(itemId + "\t=\t" + itemIdLinkMap.get(itemId));
                String url = itemIdLinkMap.get(itemId);
                // 拿数据去
                Document doc = null;
                try {
                    // 解决UnsupportedMimeTypeException，ignoreContentType(true)
                    doc = Jsoup.connect(url).headers(SpiderHeader.headers).ignoreContentType(true).timeout(30000).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert doc != null : "没有获取到文档";
                //System.out.println(doc.toString());

                // 解析html，获得商品url
                String regex = "^https://img.alicdn.com/imgextra/i.*";
                Pattern p = Pattern.compile(regex);
                StringBuilder stringBuilder = new StringBuilder();
                Elements elements = doc.select("div[data-title=\"模特效果图\"]");
                System.out.println("详情图elements size 期望是1，实际上是：" + elements.size());
                if (elements.size() == 1) {
                    // 可以进一步筛选的情况
                    for (Element e : elements) {
                        Elements elements2 = e.getElementsByTag("img");
                        for (Element e2 : elements2) {
                            String contents = e2.attr("src");
                            Matcher m = p.matcher(contents);
                            while (m.find()) {
                                stringBuilder.append(contents.substring(m.start(), m.end()));
                                stringBuilder.append(",");
                            }
                        }
                    }
                } else {
                    LOGGER.info("item ID\t{} 爬取所有图片，没有过滤", entry.getKey());
                    // 这种情况，我们就拿所有的图像
                    Elements elements2 = doc.getElementsByTag("img");
                    for (Element e2 : elements2) {
                        String contents = e2.attr("src");
                        Matcher m = p.matcher(contents);
                        while (m.find()) {
                            stringBuilder.append(contents.substring(m.start(), m.end()));
                            stringBuilder.append(",");
                        }
                    }
                }
                // 该itemID的imgs链接已经拿到，转为String,结合 SPU_code，存入数据库
                productSpu.setSpiderImgsAll(stringBuilder.toString());
                records.add(productSpu);
            } else System.out.println("itemId\t" + itemId + "'s imgs_all_url is null\t" + itemId);
            if (size % 100 == 0) {

                SqlUtils.updateImgsUrlByBatch(SqlEnum.BATCH_UPDATE_IMGS_URL.getDesc(), records);
                records.clear();
            }
        }
        System.out.println("更新数量 : " + size + ",\t期望更新的总数量：" + (spuIDMap.size()));
        // batch批量插入
        SqlUtils.updateImgsUrlByBatch(SqlEnum.BATCH_UPDATE_IMGS_URL.getDesc(), records);
    }

    // 仅仅留下我们本次需要找图像url的spucode
    public static HashMap<String, String> filtrationSpuCode(HashMap<String, String> spuIDMap, String todaySpu) {
        Set<String> spuSetThisTime = ReadThisTimeSpuCodeFile.readSpuFile(todaySpu,"txt");
        // 删除不要的code
        for (Iterator<Map.Entry<String, String>> it = spuIDMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = it.next();
            String spuCode = (String) entry.getKey();
            if (!spuSetThisTime.contains(spuCode)) {
                it.remove();
            }
        }
        return spuIDMap;
    }

    public static void main(String[] args) {
        GetAllimgsUrlImpl a = new GetAllimgsUrlImpl();
//        a.getAllImgsUrlFromApi();
    }
}
