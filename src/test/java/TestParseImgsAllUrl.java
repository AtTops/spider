import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import spider.mglp.util.SpiderHeader;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>pakage: spider.mglp.service.impl,descirption:</p>
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/14 上午11:43</pre>
 */
public class TestParseImgsAllUrl {
    public static void main(String[] args) {
        String regex = "^https://img.alicdn.com/imgextra/i.*";
        Pattern p = Pattern.compile(regex);
        Document doc = null;
        try {
            // 解决UnsupportedMimeTypeException，ignoreContentType(true)
            doc = Jsoup.connect("https://desc.alicdn.com/i1/570/251/571258166663/TB1l6Fvj0cnBKNjSZR08qwFqFla.desc%7Cvar%5Edesc%3Bsign%5Ecfcf5cfd6b5404682199e1a834a113ab%3Blang%5Egbk%3Bt%5E1531372309").headers(SpiderHeader.headers).ignoreContentType(true).timeout(30000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert doc != null : "没有获取到文档";
        System.out.println(doc.toString());
        // 解析html，获得商品url
//        Elements elements = doc.getElementsByClass("dm_module");
        Elements elements = doc.select("div[data-title=\"模特效果图\"]");
        System.out.println("详情图elements size 期望是1，实际上是：" + elements.size());
        for (Element e : elements) {
            Elements elements2 = e.getElementsByTag("img");
            for (Element e2 : elements2) {
                String contents = e2.attr("src");
                Matcher m = p.matcher(contents);
                while (m.find()) {
                    System.out.println(contents.substring(m.start(), m.end()));
                }
            }
        }
//        Set<String> set = e.classNames();
//        String name = e.className();
        System.out.println(elements.size());
//        Iterator<String> iterator = set.iterator();
//        for (String str : set){
//            System.out.println(str);
//        }
//        for (set.iterator())
//        Elements links = doc.getElementsByTag("img");
//        for (Element e : links) {
//            String regex = "^https://img.alicdn.com/imgextra/i.*";
//            Pattern p = Pattern.compile(regex);
//            String contents = e.attr("src");
//            Matcher m = p.matcher(contents);
//            while (m.find()) {
//                System.out.println(contents.substring(m.start(), m.end()));
//            }
//        }
//
//        String sss = "aaa,aaa,ccc,,,";
//        String[] k = sss.split(",");
//        System.out.println(k.length);
//        for (String kk : k){
//            System.out.println(kk);
//        }
    }
}
