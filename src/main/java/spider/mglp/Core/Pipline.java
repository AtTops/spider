package spider.mglp.Core;

import etl.*;
import mail.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(Pipline.class);

    public static void main(String[] args) {
        String localDate = LocalDate.now().toString();
        // 1、今天在架状态的spu，写磁盘，方便之后的数据拿取
        SpuEveryday spuEveryday = new SpuEveryday();
        try {
            spuEveryday.spuTody(localDate);
        } catch (IOException e) {
            LOGGER.error("获取今天的在架状态SPU并写磁盘出了些问题！");
            LOGGER.error(e.getMessage());
        }
        // 2、爬取尺码试穿数据
        SizeChartImpl sizeChart = new SizeChartImpl();
        String[] tryRules = {"div.tryTable", "div.tablet.trytab", "div.tac.size2.tablet", "div.screenshot.png.section", "div.screenshot.section", "div.sizetable.sctable"};
        String[] sizeRules = {"div.sizeTable", "div.tablet.sizetab", "div.tac.size1.tablet", "div.screenshot.section.png", "screenshot.section.png"};
        try {
            sizeChart.downloadSizeOrTryChart(localDate, "try", tryRules);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            sizeChart.downloadSizeOrTryChart(localDate, "size", sizeRules);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        IllegalCsv illegalCsv = new IllegalCsv();
        // 3、处理不合法size
        try {
            illegalCsv.disposeSize(localDate);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        CsvToJson csvToJson = new CsvToJson();
        // 4、size转json
        try {
            csvToJson.sizeToJson(localDate);
        } catch (IOException e) {
            LOGGER.error("sizeToJson 出了些问题");
            LOGGER.error(e.getMessage());
        }
        // 5、size更新downloaded.txt
        try {
            UpdateDownloaded.updateDownloaded("size", localDate);
        } catch (IOException e) {
            LOGGER.error("更新size的downloaded.txt出了些问题");
            LOGGER.error(e.getMessage());
        }
//        6、size上传测试环境
        try {
            SizeTryToDatabase.insert("size", localDate, "test");
        } catch (IOException e) {
            LOGGER.error("size插入测试数据库出错");
            LOGGER.error(e.getMessage());
        }
        // 7、处理不合法try
        try {
            illegalCsv.disposeTry(localDate);
        } catch (IOException e) {
            LOGGER.error("处理不合法try出了些问题！");
            LOGGER.error(e.getMessage());
        }
        // 8、判断try是横向还是纵向，纵向转置，
        try {
            illegalCsv.whichNeedTranspose(localDate);
        } catch (IOException e) {
            LOGGER.error("try转置出了些问题！");
            LOGGER.error(e.getMessage());
        }
        // 9、try处理为原始json
        try {
            csvToJson.tryToJson(localDate);
        } catch (IOException e) {
            LOGGER.error("tryToJson出了些问题");
            LOGGER.error(e.getMessage());
        }
        // 10、try处理为前端需要的形式
        try {
            TryCsvTune.fields7(localDate);
            TryCsvTune.formatKg(localDate);
        } catch (IOException e) {
            LOGGER.error("TryCsvTune出了些问题！");
            LOGGER.error(e.getMessage());
        }
//         11、try更新downloaded.txt
        try {
            UpdateDownloaded.updateDownloaded("try", localDate);
        } catch (IOException e) {
            LOGGER.error("更新try的downloaded.txt出了写问题");
            LOGGER.error(e.getMessage());
        }
        // 12、try传入测试库
        try {
            SizeTryToDatabase.insert("try", localDate, "test");
        } catch (IOException e) {
            LOGGER.error("try插入测试数据库出错");
            LOGGER.error(e.getMessage());
        }
        // 13、图片 【图片张杨那边已经决定人工操作，爬虫获取图片自动化相关没有整理，代码： DownloadImgsImpl、GetAllimgsUrlImpl、Width750Delete】
        // 14、评论 【已经与任务15合并打包自动运行，详情见笔记】
        // 15、印象标签

        // 16【！！！！！！！！！！！！！更新线上库 ！！！！！！！！！！！！！！！！】
//        try {
//            SizeTryToDatabase.insert("size",localDate,"online");
//        } catch (IOException e) {
//            LOGGER.error("size插入正式数据库出错");
//            LOGGER.error(e.getMessage());
//        }
//        try {
//            SizeTryToDatabase.insert("try",localDate,"online");
//        } catch (IOException e) {
//            LOGGER.error("try插入正式数据库出错");
//            LOGGER.error(e.getMessage());
//        }
        // 17、发邮件
        float[][] result = null;
        try {
            result = ReadThisTimeSpuCodeFile.report(localDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content = "类型   在架数    库中数量    命中/在架数     命中百分比" + "\n" + "size   " + (int) result[0][0] + "       " + (int) result[0][1] + "        " + (int) result[0][2] + "/" + (int) result[0][0] + "      " + result[0][3] + "\n" + " try   " + (int) result[1][0] + "       " + (int) result[1][1] + "        " + (int) result[1][2] + "/" + (int) result[1][0] + "      " + result[1][3];
//        TODO:邮件发送验证
//        SendEmail sendEmail = new SendEmail("wang.hai@yuncloset.com", "测试", content);
//        sendEmail.send();

    }
}
