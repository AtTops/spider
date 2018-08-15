package etl;

import spider.mglp.enums.UrlEnum;

import java.io.*;
import java.util.Set;

/**
 * <p>pakage: PACKAGE_NAME</p>
 * <p>
 * descirption: 将爬虫爬取的试穿csv与尺码csv，转为json数据，一行csv为一条json
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/7 下午5:06</pre>
 */
public class SizeCsvToJson {

    public void sizeToJson(String localDate) throws IOException {
        String sizeParentFilePath = UrlEnum.CSV_SIZE_SUCCESS.getDesc() + localDate;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(UrlEnum.JSON_SIZE_SUCCESS_ADJUST.getDesc() + "size_" + localDate + ".json"));
        Set<String> sizeYesterday = ReadThisTimeSpuCodeFile.readSpuFile(UrlEnum.SPU_SIZE_SUCCESS.getDesc(), "json");

        File files = new File(sizeParentFilePath);
        File[] fs = files.listFiles();    //遍历path下的文件和目录，放在File数组中
        assert fs != null;
        for (File f : fs) {
            // 若非目录(即文件)  去掉.开头的隐藏文件
            if (!f.isDirectory() && !f.isHidden()) {
                String fileName = f.getName();
                String spucode = fileName.substring(0, 12);
                if (!sizeYesterday.contains(spucode)) { // 已经
                    try {
                        // 创建CSV读对象
                        InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
                        BufferedReader br = new BufferedReader(read);
                        String txt;

                        String[] headers = null;

                        // 标记第一行表头
                        boolean flag = true;
                        while ((txt = br.readLine()) != null) {
                            StringBuilder builder = new StringBuilder();
                            if (flag) {
                                headers = txt.split(",");
                                headers[0] = "尺码";
                                flag = false;
                                continue;
                            }
                            String[] values = txt.split(",");
                            builder.append("{\"spu\":\"").append(spucode).append("\",");
                            for (int i = 0; i < headers.length; i++) {
                                try {
                                    builder.append("\"").append(headers[i]).append("\":\"").append(values[i]).append("\",");

                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println(fileName);
                                }
                            }
                            String s = builder.toString().substring(0, builder.toString().length() - 1) + "}\n";
                            bufferedWriter.write(s);
                        }

                    } catch (IOException e) {
                        System.out.println(fileName);
                        e.printStackTrace();
                    }
                }
                else{
                    f.delete();
                    System.out.println(fileName);
                }
            }
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
