package etl;

import org.joda.time.LocalDate;
import spider.mglp.enums.UrlEnum;

import java.io.*;

/**
 * <p>pakage: spider.mglp.util</p>
 * <p>
 * descirption:将试穿表调整为前端期望的形式,与CsvToJson.java衔接，形成pipline
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/8 下午4:46</pre>
 */
public class TryCsvTune {
    private static String TRY_PATH_WRITE_TMP = UrlEnum.TRY_PATH_WRITE_TMP.getDesc();

    /**
     * 处理数据为期望的7个列，未处理体重格式化
     *
     * @throws IOException IOException
     */
    public static void fields7(String localDate) throws IOException {
        // 原始拼接生成
        String pathRead = UrlEnum.JSON_TRY_SUCCESS.getDesc() + "try_nochange_" + localDate + ".json";
        InputStreamReader read = new InputStreamReader(new FileInputStream(new File(pathRead)));
        // 目标文件
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(TRY_PATH_WRITE_TMP, false));
        BufferedReader br = new BufferedReader(read);
        String text;
        while ((text = br.readLine()) != null) {
            // 首先，去掉最后的花括号
            text = text.substring(0, text.length() - 1);
            String[] values = text.split(",");
            String[] newValues = new String[7];
            // 一、spuCode,肯定是OK的
            newValues[0] = values[0];
            // 二、试穿人
            newValues[1] = ",\"试穿人\"" + values[1].substring(values[1].lastIndexOf(":"));
            // 三、四：身高与体重，分为3种情况
            // 1、身高体重合起来的
            boolean find = false;
            for (int i = 2; i < values.length; i++) {
                int index = values[i].lastIndexOf(":");
                if (values[i].substring(1, index - 1).equals("身高/体重")) {
                    String value = values[i].substring(index + 2, values[i].length() - 1);
                    String[] cmAndkg = value.split("\\s+|/");
                    newValues[2] = ",\"身高\":" + "\"" + cmAndkg[0] + "\"";
                    newValues[3] = ",\"体重\":" + "\"" + cmAndkg[1] + "\"";
                    find = true;
                    break;
                }
            }
            // 2、分开的情况
            if (!find) {
                boolean find2_s = false;
                boolean find2_t = false;
                for (int i = 2; i < values.length; i++) {
                    int index = values[i].lastIndexOf(":");
                    if (values[i].substring(1, 3).equals("身高")) {
                        String value = values[i].substring(index);
                        newValues[2] = ",\"身高\"" + value;
                        find2_s = true;
                        break;
                    }
                }
                for (int i = 2; i < values.length; i++) {
                    int index = values[i].lastIndexOf(":");
                    if (values[i].substring(1, 3).equals("体重")) {
                        String value = values[i].substring(index);
                        newValues[3] = ",\"体重\"" + value;
                        find2_t = true;
                        break;
                    }
                }
                // 3、其他情况，还没有匹配，则设为?
                if (!find2_s) {
                    newValues[2] = ",\"身高\":\"???\"";
                }
                if (!find2_t) {
                    newValues[3] = ",\"体重\":\"??\"";
                }
            }
            // 五：常穿尺码
            boolean usualSize = false;
            for (int i = 2; i < values.length; i++) {
                int index = values[i].lastIndexOf(":");
                if (values[i].substring(0, index).matches("\".*常.*尺码.*\"|\"平时试穿尺码\"|\"平时穿着尺码\"")) {
                    newValues[4] = ",\"常穿尺码\"" + values[i].substring(index);
                    usualSize = true;
                    break;
                }
            }
            if (!usualSize) {
                newValues[4] = ",\"常穿尺码\"" + ":\"?\"";
            }
            //  六、试穿尺码
            boolean trySize = false;
            for (int i = 2; i < values.length; i++) {
                int index = values[i].lastIndexOf(":");
                if (values[i].substring(0, index).matches("\".*试.*尺码\"|\".*试.*尺码（.*\"")) {
                    newValues[5] = ",\"试穿尺码\"" + values[i].substring(index);
                    trySize = true;
                    break;
                }
            }
            if (!trySize) {
                newValues[5] = ",\"试穿尺码\"" + ":\"?\"";
            }
            //  七、试穿感受
            boolean tryFeel = false;
            for (int i = 2; i < values.length; i++) {
                int index = values[i].lastIndexOf(":");
                if (values[i].substring(0, index).matches("\".*试穿感受\"")) {
                    newValues[6] = ",\"试穿感受\"" + values[i].substring(index) + "}";
                    tryFeel = true;
                    break;
                }
            }
            if (!tryFeel) {
                newValues[6] = ",\"试穿感受\"" + ":\"?\"}";
            }
            // 试穿尺码和试穿感受在一起的 (试穿尺码及感受) 需要拆开
            boolean flag = true;
            for (int i = 2; i < values.length; i++) {
                try {
                    String value = values[i];
                    int index = values[i].lastIndexOf(":");
                    if (value.substring(0, index).matches("\".*试穿尺码及感受.*\"")) {
                        String valueTup = value.substring(index);
                        if (valueTup.contains("/")) {
                            String[] ss = valueTup.split("/");
                            newValues[5] = ",\"试穿尺码\"" + ss[0] + "\"";
                            newValues[6] = ",\"试穿感受\":\"" + ss[1] + "}";
                        } else {
                            newValues[5] = ",\"试穿尺码\"" + valueTup.substring(0, 3) + "\"";
                            newValues[6] = ",\"试穿感受\":\"" + valueTup.substring(3) + "}";
                        }
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    flag = false;
                    System.out.println("tmp文件数组越界的地方： " + text);
                }
            }
            if (flag) {
                String finalValue = newValues[0] + newValues[1] + newValues[2] + newValues[3] + newValues[4] + newValues[5] + newValues[6];
                bufferedWriter.write(finalValue);
                bufferedWriter.write("\n");
            }
        }
        br.close();
        bufferedWriter.flush();
        bufferedWriter.close();
        read.close();
    }

    public static void formatKg(String localDate) throws IOException {
        InputStreamReader read = new InputStreamReader(new FileInputStream(new File(TRY_PATH_WRITE_TMP)), "utf-8");
        // 处理体重
        String write2 = UrlEnum.JSON_TRY_SUCCESS_ADJUST.getDesc() + "try_" + localDate + ".json";

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(write2));
        BufferedReader br = new BufferedReader(read);
        String text;
        while ((text = br.readLine()) != null) {
            String[] values = text.split(",");
            String value = values[3];
            // 删除不要的行
            if (value.contains("XX") || value.contains("xx") || values[2].contains("XX") || values[2].contains("xx") || values[6].contains("?")) {
                continue;
            }
            String weight = value.substring(value.lastIndexOf(":") + 2, value.length() - 1);
            if (!weight.contains("Kg") && !weight.contains("kg") && !weight.contains("KG") && !weight.contains("kG")) {
                try {
                    int weightInt = Integer.parseInt(weight);
                    // 大于67的，就认为是斤，除以2，这个阈值，有待商榷
                    values[3] = weightInt > 67 ? "\"体重\":\"" + weightInt / 2 + "kg\"" : "\"体重\":\"" + weight + "kg\"";
                } catch (NumberFormatException e) {
                    // TODO：打log
//                    System.out.println(weight + "转换失败");
                }
            }
            StringBuilder newStr = new StringBuilder();
            for (String s : values) {
                newStr.append(s).append(",");
            }
            bufferedWriter.write(newStr.substring(0, newStr.length() - 1));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        System.out.println("try json处理完毕！");
    }
}