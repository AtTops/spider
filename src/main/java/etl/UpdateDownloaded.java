package etl;

import spider.mglp.enums.UrlEnum;

import java.io.*;
import java.util.Set;

/**
 * <p>pakage: etl</p>
 *
 * descirption:将今天更新的size与try的json文件更新到downloaded.txt文件
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/15 下午12:52</pre>
 */
public class UpdateDownloaded {
    /**
     * @param type      size还是try
     * @param localDate 日期
     */
    public static void updateDownloaded(String type, String localDate) throws IOException {
        String fileNameAndPath;
        String downloadedPath;
        if (type.equals("size")) {
            fileNameAndPath = UrlEnum.JSON_SIZE_SUCCESS_ADJUST.getDesc() + "size_" + localDate + ".json";
            downloadedPath = UrlEnum.SPU_SIZE_SUCCESS.getDesc();
        } else {
            fileNameAndPath = UrlEnum.JSON_TRY_SUCCESS_ADJUST.getDesc() + "try_" + localDate + ".json";
            downloadedPath = UrlEnum.SPU_TRY_SUCCESS.getDesc();
        }
        Set<String> spuDownloaded = ReadThisTimeSpuCodeFile.readSpuFile(fileNameAndPath, "json");
        FileWriter fileWriter = new FileWriter(new File(downloadedPath),true);
        for (String line : spuDownloaded){
            fileWriter.write(line);
            fileWriter.write("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
