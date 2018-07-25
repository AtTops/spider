package spider.mglp.service.impl;

import com.csvreader.CsvWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import spider.mglp.enums.UrlEnum;
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

        Document doc;
        InputStream is = new URL("https://desc.alicdn.com/i2/560/101/566108943940/TB1aTPTDKuSBuNjSszi8qvq8pla.desc%7Cvar%5Edesc%3Bsign%5E7ea4dfc39f3016d97e50f6227486b9d7%3Blang%5Egbk%3Bt%5E1532062377").openStream();
//        InputStream is = new URL("https://desc.alicdn.com/i8/560/210/567215404672/TB1O141lFooBKNjSZFP8qta2Xla.desc%7Cvar%5Edesc%3Bsign%5E8c321adf54427e4f878bbe66e3128af9%3Blang%5Egbk%3Bt%5E1532398225").openStream();
        String html = "";
        try {
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
        Elements elements = doc.select("div.screenshot.section.chrome");
//        Elements elements = doc.select("div.tablet.sizeTable");
        // 选择到试穿div
        Element last = elements.last();
        // 选择到table
        Elements trs = last.select("table").select("tr");
        int column = trs.size();
        System.out.println("该试穿表的行数是：" + column);
        String spuCode = "12345678";
        String filePathAndName = UrlEnum.BASIC_OUTFILE_PATH.getDesc() + spuCode + "_" + column + ".csv";
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
//                System.out.println(val.replaceAll("\\\\","").trim());
                values[j] = val.replaceAll("\\\\","\t").trim();
            }
            csvWriter.writeRecord(values);
            csvWriter.flush();

        }
        csvWriter.close();


//        SizeChartImpl sizeChart = new SizeChartImpl();
//        sizeChart.downloadSizeChart();
    }
}
