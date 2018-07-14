package spider.mglp.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import spider.mglp.enums.UrlEnum;
import spider.mglp.util.SpiderHeader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * <p>pakage: spider.mglp.service.impl,descirption:</p>
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/11 下午1:44</pre>
 */
public class test {
    public static void main(String[] args) throws IOException {
//        String str = "http://item.taobao.com/item.htm?id=569631238615";
//        String id = str.replaceAll("[^\\d{8,}]","");
//        System.out.println(id);
        Document doc = null;

        String str = "https://h5api.m.taobao.com/h5/mtop.taobao.detail.getdetail/6.0/?v=6.0&type=jsonp&data=%7B%22itemNumId%22%3A%22571151905544%22%2C%22exParams%22%3A%22%7B%5C%22id%5C%22%3A%5C%22571597818260%5C%22%7D%22%2C%22detail_v%22%3A%223.1.1%22%2C%22ttid%22%3A%222018%40taobao_iphone_9.9.9%22%2C%22utdid%22%3A%22123123123123123%22%7D";
        String sss = "https://h5api.m.taobao.com/h5/mtop.taobao.detail.getdetail/6.0/?v=6.0&type=jsonp&data=%7B%22itemNumId%22%3A%22571151905544%22%2C%22exParams%22%3A%22%7B%5C%22id%5C%22%3A%5C%22571597818260%5C%22%7D%22%2C%22detail_v%22%3A%223.1.1%22%2C%22ttid%22%3A%222018%40taobao_iphone_9.9.9%22%2C%22utdid%22%3A%22123123123123123%22%7D";
        //InputStream is = new URL("https://h5api.m.taobao.com/h5/mtop.taobao.detail.getdetail/6.0/?dataType=jsonp&data=%7B%22itemNumId%22%3A%103118251856%22%2C%22").openStream();
//        InputStream is = new URL(UrlEnum.API_PRIFIX.getDesc() + "571151905544" + UrlEnum.API_SUFFIX.getDesc()).openStream();
        InputStream is = new URL(str).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            System.out.println(jsonText);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // 解决UnsupportedMimeTypeException，ignoreContentType(true)
            doc = Jsoup.connect(str).headers(SpiderHeader.headers).ignoreContentType(true).timeout(30000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert doc != null;
//        System.out.println(doc.toString());

        String test = "https://item.taobao.com/item.htm?id=568944784760&amp;amp;spm=2014.12440355.0.0";
        System.out.println(test.lastIndexOf("taobao"));
        System.out.println(test.substring(test.lastIndexOf("id=") + 3, test.lastIndexOf("id=") + 15));

        String jsonText = "{\"api\":\"wdetail\",\"v\":\"6.0\",\"ret\":[\"SUCCESS::调用成功\"],\"data\":{\"trade\":{\"redirectUrl\":\"http://h5.m.taobao.com/detailplugin/expired.html?isInvalid=true&itemId=552233094596&isSuperAct=false\"}}}";
        ObjectMapper objMapper = new ObjectMapper();
        JsonNode root = null;
        root = objMapper.readTree(jsonText);
        System.out.println("000000000000000");
        System.out.println(root.findPath("images").toString());
        if (root.findPath("images").toString().length() > 2) {
            System.out.println("111111111111111111111");
            String[] images = root.findPath("images").toString().split(",");
            int imagesCount = images.length;
            StringBuilder stringBuilder = null;
            stringBuilder = new StringBuilder();
            for (int i = 0; i < imagesCount; i++) {
                System.out.println("2222222222222222");

                stringBuilder.append(images[i].replaceAll("[\\[\\]\"]", " ").trim().substring(2));
                if (i != imagesCount - 1)
                    stringBuilder.append(",");
            }
            // 如果确实有记录到链接，则给赋值数据库
            if (stringBuilder.toString().trim().length() > 5) {
                System.out.println("3333333333333333");
                System.out.println(stringBuilder.toString());
            }

        }
    }
}
