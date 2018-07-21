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
        System.out.println("spuSet size:  "+spuSet.size());
        return spuSet;
    }
    public static HashSet<String> countSpuFileLocal(String path) {
        HashSet<String> spuSetLocal = new HashSet<>();

        // 读取文件spucode过滤

        File files = new File(path);
        File[] fs = files.listFiles();    //遍历path下的文件和目录，放在File数组中
        assert fs != null;
        for (File f : fs) {
            // 若非目录(即文件)  去掉.开头的隐藏文件
            if (!f.isDirectory() && !f.isHidden()) {
                String startName = f.getName();
                String spucode = startName.substring(0, 12);
                spuSetLocal.add(spucode);

            }
        }
        System.out.println("spuSet size:  "+spuSetLocal.size());
        return spuSetLocal;
    }

    public static void main(String[] args) {
        ReadThisTimeSpuCodeFile.countSpuFileLocal("/Users/wanghai/Downloads/to_upload/0");
    }
}
