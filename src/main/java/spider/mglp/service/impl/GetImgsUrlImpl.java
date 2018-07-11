package spider.mglp.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spider.mglp.enums.SqlEnum;
import spider.mglp.enums.UrlEnum;
import spider.mglp.pojo.ProductSpu;
import spider.mglp.pojo.ProductSpuWithBLOBs;
import spider.mglp.service.GetImgsUrl;
import spider.mglp.util.SpiderHeader;
import spider.mglp.util.SqlUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>pakage: spider.mglp.service.impl,descirption:</p>
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/10 下午3:17</pre>
 */
public class GetImgsUrlImpl implements GetImgsUrl {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetImgsUrlImpl.class);
    private int count = 0;
    private int size = 0;
    // ArrayList<? super ProductSpu> records = new ArrayList<>(256);

    @Override
    public void getImgsUrlFromApi() throws IOException, InterruptedException {
        // 获取所有的spu_code和taobao_link
        HashMap<String, String> hashMap = SqlUtils.getSpuCodeAndTbLink();
        ArrayList<ProductSpuWithBLOBs> records = new ArrayList<>(256);

        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            // 获得itemId————————https://item.taobao.com/item.htm?id=568944784760&amp;amp;spm=2014.12440355.0.0
            int begin = entry.getValue().lastIndexOf("?id=") + 4;

            String itemId = entry.getValue().substring(begin, begin + 12);
            // 重新设置value，该value用来请求接口数据
            String newUrl = UrlEnum.API_PRIFIX.getDesc() + itemId + UrlEnum.API_SUFFIX.getDesc();
            System.out.println("taobao_link:" + entry.getValue());
            System.out.println("itemId:" + itemId);
            count++;

            hashMap.put(entry.getKey(), UrlEnum.API_PRIFIX.getDesc() + itemId + UrlEnum.API_SUFFIX.getDesc());
        }

        System.out.println("期望获取的总记录数：" + hashMap.size());
        // 遍历hashMap，请求接口数据，赋值给ProductSpuWithBLOBs对象
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            /**
             * 这个方式不行，会构造<html></html>等标签，导致JackSon解析出错
             */
//            Document doc = null;
//            try {
//                // 解决UnsupportedMimeTypeException，ignoreContentType(true)
//                doc = Jsoup.connect(entry.getValue()).headers(SpiderHeader.headers).ignoreContentType(true).timeout(30000).get();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            assert doc != null;
            String jsonText;
            StringBuilder sb = null;
            InputStream is = new URL(entry.getValue()).openStream();
            System.out.println("接口地址：" + entry.getValue());
            try {
                BufferedReader brd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                sb = new StringBuilder();
                int cp;
                while ((cp = brd.read()) != -1) {
                    sb.append((char) cp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            jsonText = sb.toString();
            // 无效的返回数据
            if (jsonText.length() > 5) {
                System.out.println("返回的JSON数据：" + jsonText);
                System.out.println("实际是第 " + size++ + "条记录");
                ProductSpuWithBLOBs productSpu = new ProductSpuWithBLOBs();
                productSpu.setSpuCode(entry.getKey());
                // 开始解析获得的接口数据
                ObjectMapper objMapper = new ObjectMapper();
                JsonNode root = null;
                try {
                    root = objMapper.readTree(jsonText);
                } catch (JsonParseException e) {
                    System.out.println(jsonText);
                    System.out.println(entry.getKey());
                }
                // images_url，这里大都只有5条数据，TODO：寻找其他地方出现的图片
                if (root.findPath("images").toString().length() > 2) {
                    String[] images = root.findPath("images").toString().split(",");
                    int imagesCount = images.length;
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < imagesCount; i++) {
                        stringBuilder.append(images[i].replaceAll("[\\[\\]\"]", " ").trim().substring(2));
                        if (i != imagesCount - 1)
                            stringBuilder.append(",");
                    }
                    productSpu.setSpiderImgs(stringBuilder.toString().trim());
                    // 把该对象放入ArrayList
                    records.add(productSpu);
                    // 睡眠随机秒，再接着爬数据（0.5秒到3秒的区间）
                    Random rand = new Random();
                    Thread.sleep(rand.nextInt(3000) + 500);
                }
            }
        }
        System.out.println("更新数量 : " + records.size() + ",\t期望更新的总数量：" + (count-1));
        // batch批量插入
        SqlUtils.updateImgsUrlByBatch(SqlEnum.BATCH_UPDATE_IMGS_URL.getDesc(), records);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GetImgsUrlImpl p = new GetImgsUrlImpl();
        p.getImgsUrlFromApi();
    }
}