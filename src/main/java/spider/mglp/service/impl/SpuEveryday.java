package spider.mglp.service.impl;

import spider.mglp.enums.UrlEnum;
import spider.mglp.util.SqlUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 *
 * descirption: 统计今天在线的所有spu以及淘宝链接
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/10 下午2:28</pre>
 */
public class SpuEveryday {
    public Map<String, String> spuTody(LocalDate localDate) throws IOException {
        String fileName = UrlEnum.SPU_EVERYDAY_PATH.getDesc() + "spu_" + localDate.toString() + ".txt";
        FileWriter fileWriter = new FileWriter(new File(fileName));
        // 查询数据库，获取所有的spu_code和taobao_link
        HashMap<String, String> spuIDMap = SqlUtils.getSpuCodeAndTbLink();
        for (Map.Entry<String, String> e : spuIDMap.entrySet()) {
            System.out.println(e.getKey() + "====" + e.getValue());
            fileWriter.write(e.getKey() + "," + e.getValue());
            fileWriter.write("\n");
        }
        fileWriter.flush();
        fileWriter.close();
        return spuIDMap;
    }

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        SpuEveryday spuEveryday = new SpuEveryday();
        try {
            spuEveryday.spuTody(localDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
