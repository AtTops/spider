package etl;

import com.csvreader.CsvWriter;
import org.jsoup.select.Elements;
import spider.mglp.enums.UrlEnum;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>pakage: etl</p>
 * <p>
 * descirption:处理size、try中的不符合对的csv，比如size、try混乱，size中有其他信息，比如空行，比如多商品信息，等等
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/14 下午3:30</pre>
 */
public class IllegalCsv {
    private static final String[] SIZE_HEADER = {"尺", "s", "S"};
    private static final String[] SIZE_BODY = {"XS", "S", "M", "L", "xs", "s", "m", "l"};
    private static final String[] TRY_HEADER = {"试穿者", "试穿人"};
    private static final String[] TRY_BODY = {"XS", "S", "M", "L", "xs", "s", "m", "l"};

    /**
     * 处理size中的不合法数据
     *
     * @param localDate
     * @throws IOException
     */
    public void disposeSize(String localDate) throws IOException {
        String sizeParentFilePath = UrlEnum.CSV_SIZE_SUCCESS.getDesc() + localDate;
        String sizeResultFilePath = sizeParentFilePath + "/";
        File files = new File(sizeParentFilePath);
        File[] fs = files.listFiles();    //遍历path下的文件和目录，放在File数组中
        for (File f : fs) {
            // 若非目录(即文件)  去掉.开头的隐藏文件
            if (!f.isDirectory() && !f.isHidden()) {
                String fileName = f.getName();
                String spucode = fileName.substring(0, 12);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
                String line;
                boolean findFlag = false;
                FileWriter fileWriter = null;
                while ((line = bufferedReader.readLine()) != null) {
                    // 直到匹配到尺码表正确的开始行
                    String firstValue = line.split(",")[0];
                    if (!findFlag) {
                        if (!myContains(firstValue, SIZE_HEADER) && !firstValue.matches(".*试.*尺码")) {
                            // do nothing , next loop
                        } else {
                            fileWriter = new FileWriter(new File(sizeResultFilePath + spucode + "_size.csv"));
                            fileWriter.write(deleteRedundancyCommon(line));
                            fileWriter.write("\n");
                            findFlag = true;
                        }
                    }// 找到并写入尺码表正确开始的那一行后，需要继续写入尺码信息，并抛弃 不是尺码信息的数据
                    else if (myContains(firstValue, SIZE_BODY)) {
                        fileWriter.write(deleteRedundancyCommon(line));
                        fileWriter.write("\n");
                    } else break;
                }
                if (fileWriter != null) {
                    f.delete();
                    fileWriter.close();
                } else if (!findFlag) {
                    f.delete();
                    System.out.println("不合法的size： " + fileName);
                }
            }
        }
    }

    /**
     * 删除不合法的try数据
     *
     * @param localDate
     * @throws IOException
     */
    public void disposeTry(String localDate) throws IOException {
        String sizeParentFilePath = UrlEnum.CSV_TRY_SUCCESS.getDesc() + localDate;
        String sizeResultFilePath = sizeParentFilePath + "/";
        File files = new File(sizeParentFilePath);
        File[] fs = files.listFiles();    //遍历path下的文件和目录，放在File数组中
        for (File f : fs) {
            // 若非目录(即文件)  去掉.开头的隐藏文件
            if (!f.isDirectory() && !f.isHidden()) {
                String fileName = f.getName();
                String spucode = fileName.substring(0, 12);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
                String line;
                boolean findFlag = false;
                FileWriter fileWriter = null;
                while ((line = bufferedReader.readLine()) != null) {
                    // 直到匹配到尺码表正确的开始行
                    String firstValue = line.split(",")[0];
                    if (!findFlag) {
                        if (!myContains(firstValue, TRY_HEADER)) {
                            // do nothing , next loop
                        } else {
                            fileWriter = new FileWriter(new File(sizeResultFilePath + spucode + "_try.csv"));
                            fileWriter.write(deleteRedundancyCommon(line));
                            fileWriter.write("\n");
                            findFlag = true;
                        }
                    }// 找到并写入尺码表正确开始的那一行后，需要继续写入尺码信息，并抛弃 不是尺码信息的数据
                    else {
                        fileWriter.write(deleteRedundancyCommon(line));
                        fileWriter.write("\n");
                    }
                }
                if (fileWriter != null) {
                    fileWriter.close();
                    // 删除原文
                    f.delete();
                } else if (!findFlag) {
                    f.delete();
                    System.out.println("不合法的try: " + fileName);
                }
            }
        }
    }

    public boolean myContains(String line, String[] ifMatch) {
        for (String value : ifMatch) {
            if (line.contains(value)) {
                return true;
            }
        }
        return false;
    }

    public String deleteRedundancyCommon(String str) {
        while (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 识别出需要转置的csv文件,调用转置函数转置
     *
     * @param localDate
     * @throws IOException
     */
    public void whichNeedTranspose(String localDate) throws IOException {
        String tryParentFilePath = UrlEnum.CSV_TRY_SUCCESS.getDesc() + localDate;
        File files = new File(tryParentFilePath);
        File[] fs = files.listFiles();
        for (File f : fs) {
            if (!f.isDirectory() && !f.isHidden()) {
                String fileName = f.getName();
                BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
                String line = bufferedReader.readLine();
                if (!line.contains("码")) {
                    // 这些是需要转置的
                    int column = line.split(",").length;
                    transposeCsv(f, column, tryParentFilePath);
                    System.out.println("转置： " + fileName);
                }
            }
        }
    }

    /**
     * csv转置
     *
     * @param file
     */
    public void transposeCsv(File file, int column, String tryParentFilePath) throws IOException {
        String spuCode = file.getName().substring(0, 12);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        ArrayList<String> arrayList = new ArrayList<>(16);
        while ((line = bufferedReader.readLine()) != null) {
            arrayList.add(line);
        }
        int row = arrayList.size();
        bufferedReader.close();
        file.delete();
        String[][] matrix = new String[column][row];
        int transposeColumn = 0;
        for (String rowValue : arrayList) {
            int transposeRow = 0;
            String[] values = rowValue.split(",");
            for (String str : values) {
                matrix[transposeRow++][transposeColumn] = str;
            }
            transposeColumn += 1;
        }
        // 创建CSV写对象
        CsvWriter csvWriter = new CsvWriter(tryParentFilePath + "/" + spuCode + "_try_.csv", ',', Charset.forName("UTF-8"));
        // 获取tr
        for (int i = 0; i < column; i++) {
            csvWriter.writeRecord(matrix[i]);
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public static void main(String[] args) {
        IllegalCsv illegalCsv = new IllegalCsv();
        try {
            illegalCsv.whichNeedTranspose("2018-08-14");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
