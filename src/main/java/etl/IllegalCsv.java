package etl;

import spider.mglp.enums.UrlEnum;

import java.io.*;
import java.time.LocalDate;
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

    /**
     * 处理size中的不合法数据
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
                    fileWriter.close();
                    // 删除原文
                    f.delete();
                } else {
                    System.out.println(fileName);
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

    public static void main(String[] args) {
        String localDate = LocalDate.now().toString();
        IllegalCsv illegalCsv = new IllegalCsv();
        try {
            illegalCsv.disposeSize(localDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
