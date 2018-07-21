package spider.mglp.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 * <p>
 * descirption:
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/19 下午8:59</pre>
 */
public class TsetCodeInLocal {
    public static void main(String[] args) {
        Set<String> spuSet = null;
        Set<String> spuLocalSet = new HashSet<>();
        File file = new File(
                "/Users/wanghai/Desktop/spucode.txt");
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), "utf-8");
            if (file.isFile() && file.exists()) {

                spuSet = new HashSet<String>();
                BufferedReader br = new BufferedReader(read);
                String txt = null;

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

        // 遍历文件夹下面的文件
        String path = "/Users/wanghai/IdeaProjects/detail_imgs的副本";        //要遍历的路径
        String newpath = "/Users/wanghai/IdeaProjects/img_19的副本";        // 存储新路径
        File files = new File(newpath);        // 获取其file对象
        File[] fs = files.listFiles();    // 遍历path下的文件和目录，放在File数组中
        assert fs != null;
        int count = 0;
        for (File f : fs) {                    //遍历File[]数组
            if (!f.isDirectory()) {    // 若非目录(即文件)
                String startName = f.getName();
                if (startName.length() > 12) { // 去掉.开头的隐藏文件
                    String spucode = startName.substring(0, 12);
                    spuLocalSet.add(spucode);
                }
            }
        }
        System.out.println("本地的spucode， 有" + spuLocalSet.size());
        spuSet.removeAll(spuLocalSet);
        System.out.println("本地的spucode， 在云棚给的 有" + spuSet.size());
        for (String code : spuSet) {
            System.out.println(code);
        }
    }
}
