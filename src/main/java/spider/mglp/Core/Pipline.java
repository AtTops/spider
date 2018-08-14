package spider.mglp.Core;

import etl.IllegalCsv;
import spider.mglp.service.impl.SizeChartImpl;
import spider.mglp.service.impl.SpuEveryday;

import java.io.IOException;
import java.time.LocalDate;

/**
 * <p>pakage: spider.mglp.Core</p>
 * <p>
 * descirption:爬虫流水线作业调度
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/13 下午2:45</pre>
 */
public class Pipline {
    public static void main(String[] args) {
        String localDate = LocalDate.now().toString();
        // 1、今天在架状态的spu，写磁盘，方便之后的数据拿取
        SpuEveryday spuEveryday = new SpuEveryday();
        try {
            spuEveryday.spuTody(localDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 2、爬取尺码试穿数据
        SizeChartImpl sizeChart = new SizeChartImpl();
        String[] sizeRules = {"div.sizeTable", "div.tablet.sizetab", "div.tac.size1.tablet", "div.screenshot.section.png","screenshot.section.png"};
        String[] tryRules = {"div.tryTable", "div.tablet.trytab", "div.tac.size2.tablet","div.screenshot.png.section","div.screenshot.section","div.sizetable.sctable"};
        try {
            sizeChart.downloadSizeOrTryChart(localDate, "try", tryRules);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sizeChart.downloadSizeOrTryChart(localDate, "size", sizeRules);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 3、处理不合法size
        IllegalCsv illegalCsv = new IllegalCsv();
        try {
            illegalCsv.disposeSize(localDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 3、判断try是横向还是纵向，纵向转置
        // 3、处理为json
        // 4、更新downloaded.txt
        // 5、计算占比，发邮件
    }
}
