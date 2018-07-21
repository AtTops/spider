package spider.mglp.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import spider.mglp.util.SpiderHeader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SizeChartImpl Tester.
 *
 * @author <wang>
 * @version 1.0
 * @since <pre>07/15/2018</pre>
 */
public class SizeChartImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: downloadSizeChart()
     */
    @Test
    public void testDownloadSizeChart() throws Exception {

        Document doc = null;
// <html>
// <head></head>
// <body>

// </body>
//</html>
        // 解析html，获得商品url
//        Elements elements = doc.select("div.[screenshot section png]");
//        Elements elements = doc.getElementsByClass("screenshot section png");
//        Element e = elements.last();
//        System.out.println(e.toString());

        InputStream is = new URL("https://desc.alicdn.com/i1/570/251/571258166663/TB1l6Fvj0cnBKNjSZR08qwFqFla.desc%7Cvar%5Edesc%3Bsign%5Ecfcf5cfd6b5404682199e1a834a113ab%3Blang%5Egbk%3Bt%5E1531372309").openStream();
        String html = "";
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "GBK"));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            html = sb.toString();
//            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc = Jsoup.parse(html);
        // 解析html，获得商品url
        String regex = "^https://img.alicdn.com/imgextra/i.*";
        Pattern p = Pattern.compile(regex);
        StringBuilder stringBuilder = new StringBuilder();
        Elements elements = doc.select("div.screenshot.section.png");
        // 选择到试穿div
        Element last = elements.last();
        // 选择到table
        Elements trs = last.select("div.tablet").select("tr");
        System.out.println("详情图elements size 期望是1，实际上是：" + trs.size());
        // 获取tr
        for (int i = 0; i < trs.size(); i++) {
            //获取每一行的列
            Elements tds = trs.get(i).select("td");
            for (int j = 0; j < tds.size(); j++) {
                //对每一行中的某些你需要的列进行处理

                //获取第i行第j列的值

                String oldClose = tds.get(j).text();

                //接下来，进行你的操作
                System.out.println(oldClose);
                System.out.println();
            }

        }
//        Elements elements2 = e.getElementsByTag("img");
//        for (Element e2 : elements2) {
//            String contents = e2.attr("src");
//            Matcher m = p.matcher(contents);
//            while (m.find()) {
//                stringBuilder.append(contents.substring(m.start(), m.end()));
//                stringBuilder.append(",");
//            }
//        }
    }
}
