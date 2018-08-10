package spider.mglp.service.impl;

import java.io.*;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 * <p>
 * descirption:
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/8 下午1:54</pre>
 */
public class RateTojson {
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/wanghai/all_rate.json", true));
        String path = "/Users/Shared/rate/all_rate.csv";
        InputStreamReader read = new InputStreamReader(new FileInputStream(new File(path)), "utf-8");
        BufferedReader br = new BufferedReader(read);
        String txt;
        String[] headers = null;
        boolean flag = true;
        while ((txt = br.readLine()) != null) {
            StringBuilder builder = new StringBuilder();
            // 读一整行
            if (flag) {
                headers = txt.split(",");
                flag = false;
                continue;
            }
            String[] values = txt.split(",");
            int valuesLen = values.length;
            if (valuesLen > 10){
                // 有一条坏数据
                System.out.println(txt);
                continue;
            }
            // spuCode,itemId,ratesMaybe,timeFirst,nick,vipLevel,skuInfo,rateFirst,afterDays,secondRate
            for (int i = 0; i < valuesLen; i++) {
                builder.append("\"" + headers[i] + "\":\"" + values[i] + "\",");
            }if (valuesLen < headers.length){
                for (int k = valuesLen; k < headers.length; k++){
                    builder.append("\"" + headers[k] + "\":\"null\",");
                }
            }
            String s = builder.toString().substring(0, builder.toString().length() - 1) + "}\n";
            bufferedWriter.write("{" + s);
            bufferedWriter.flush();
            //System.out.println(s);
        }
        bufferedWriter.close();
    }
}
