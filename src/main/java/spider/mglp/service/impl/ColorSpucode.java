package spider.mglp.service.impl;

import spider.mglp.util.ReadThisTimeSpuCodeFile;
import spider.mglp.util.SqlUtils;

import java.io.File;
import java.util.*;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 * <p>
 * descirption:
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/19 下午6:10</pre>
 */
public class ColorSpucode {
    /**
     * @param path       需要处理的图像的路径
     * @param removePath 处理之后移动的路径
     */
    public static void addColorAndDeleteWidth(String path, String removePath) {
        HashMap<String, String> hashMap;
        // 获取（spu，color）map
        hashMap = SqlUtils.getSpuCodeAndColor();

        Set<String> spuSet = ReadThisTimeSpuCodeFile.readSpuFile("/Users/wanghai/Desktop/spucode.txt","txt");

        System.out.println("线上的spucode 有\t" + hashMap.size());
        System.out.println("云棚给的 有\t" + spuSet.size());
        // 删除不要的code
        for (Iterator<Map.Entry<String, String>> it = hashMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            String spuCode = (String) entry.getKey();
            if (!spuSet.contains(spuCode)) {
                it.remove();
            }
        }
//        for (Iterator<Map.Entry<String, String>> it = hashMap.entrySet().iterator(); it.hasNext(); ) {
//            Map.Entry entry = (Map.Entry) it.next();
//            System.out.println((String) entry.getKey() + "\t" + (String) entry.getValue());
//        }
        System.out.println("线上的spucode 在云棚给的中，有\t" + hashMap.size());

        File files = new File(path);        //获取其file对象
        File[] fs = files.listFiles();    //遍历path下的文件和目录，放在File数组中
        assert fs != null;
        int count = 0;
        for (File f : fs) {
            // 若非目录(即文件)  去掉.开头的隐藏文件
            if (!f.isDirectory() && !f.isHidden()) {
                String startName = f.getName();
                String spucode = startName.substring(0, 12);
                //System.out.println(spucode);
                if (hashMap.containsKey(spucode)) {
                    count++;
                    System.out.println(count);
//                        System.out.println("=====" + spucode);
                    String realName = spucode + "_" + hashMap.get(spucode) + startName.substring(startName.lastIndexOf("_"));
//                        System.out.println(realName);
                    File newName = new File(removePath + "/" + realName);
                    // 重命名并且移动
                    if (!f.renameTo(newName)) {
                        System.out.println((startName + "移动失败"));
                    }
                }
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        String path = "/Users/wanghai/images/img_20";
        String removePath = "/Users/wanghai/images/filter_img_20";
        ColorSpucode.addColorAndDeleteWidth(path, removePath);
    }
}