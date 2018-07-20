package spider.mglp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>pakage: spider.mglp.util</p>
 * <p>
 * descirption:
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/20 下午3:06</pre>
 */
public class ReadThisTimeSpuCodeFile {
    public static Set<String> readSpuFile(String path) {
        Set<String> spuSet = null;

        // 读取文件spucode过滤

        File file = new File(path);
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), "utf-8");
            if (file.isFile() && file.exists()) {

                spuSet = new HashSet<>();
                BufferedReader br = new BufferedReader(read);
                String txt;
                // 读取文件，将文件内容放入到set中
                while ((txt = br.readLine()) != null) {
                    spuSet.add(txt);
                }
                br.close();
            }
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spuSet;
    }
}
