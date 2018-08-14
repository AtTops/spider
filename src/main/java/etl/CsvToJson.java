package etl;

import java.io.*;

/**
 * <p>pakage: PACKAGE_NAME</p>
 *
 * descirption: 将爬虫爬取的试穿csv与尺码csv，转为json数据，一行csv为一条json
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/7 下午5:06</pre>
 */
public class CsvToJson {
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/wanghai/try_1.json", true));
        String path = "/Users/Shared/try_chart_/";
        File files = new File(path);
        File[] fs = files.listFiles();    //遍历path下的文件和目录，放在File数组中
        assert fs != null;
        for (File f : fs) {
            // 若非目录(即文件)  去掉.开头的隐藏文件
            if (!f.isDirectory() && !f.isHidden()) {
                String startName = f.getName();
                String spucode = startName.substring(0, 12);
                try {
                    // 创建CSV读对象
                    InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
                    BufferedReader br = new BufferedReader(read);
                    String txt;

                    String[] headers = null;

                    boolean flag = true;
                    while ((txt = br.readLine()) != null) {
                        txt = txt.replace("\t"," ");
                        StringBuilder builder = new StringBuilder();
                        // 读一整行
                        if (flag) {
                            headers = txt.split(",");
                            flag = false;
                            continue;
                        }
                        String[] values = txt.split(",");
                        builder.append("{\"spu\":\"").append(spucode).append("\",");
                        for (int i = 0; i < headers.length; i++) {
                            try {
                                builder.append("\"").append(headers[i]).append("\":\"").append(values[i]).append("\",");

                            }catch (ArrayIndexOutOfBoundsException e){
                                System.out.println(startName);
                            }
                        }
                        String s = builder.toString().substring(0, builder.toString().length() - 1) + "}\n";
                        bufferedWriter.write(s);
                    }

                } catch (IOException e) {
                    System.out.println(startName);
                    e.printStackTrace();
                }
            }
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
