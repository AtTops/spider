package spider.mglp.service.impl;

import java.io.File;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 * <p>
 * descirption:
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/19 下午7:53</pre>
 */
public class RenameAll {
    public static void main(String[] args) {
        String newpath = "/Users/wanghai/IdeaProjects/detail_imgs的副本 2";        //要遍历的路径
        File files = new File(newpath);        //获取其file对象
        File[] fs = files.listFiles();    //遍历path下的文件和目录，放在File数组中
        assert fs != null;
        int count = 0;
        for (File f : fs) {                    //遍历File[]数组
            if (!f.isDirectory()) {    //若非目录(即文件)，则打印
                String startName = f.getName();
                if (startName.length() > 12) {
                    count++;
                    //System.out.println(startName);
                    String spucode = startName.substring(0, 12);
                    String realName = spucode + startName.substring(startName.lastIndexOf("_"));
                    System.out.println(realName);
                    File newName = new File(newpath + "/" + realName);
                    // 重命名
                    f.renameTo(newName);
//                }
                }
            }
        }
        System.out.println(count);
    }
}
