package spider.mglp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spider.mglp.util.SqlUtils;

import java.io.File;
import java.util.HashMap;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 * <p>
 * * descirption: 把大家
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/20 下午5:49</pre>
 */
public class RenameBack {
    private static final Logger LOGGER = LoggerFactory.getLogger(RenameBack.class);

    public void renameBackPic(String path, String removePath) {
        File files = new File(path);        //获取其file对象
        File[] fs = files.listFiles();    //遍历path下的文件和目录，放在File数组中
        assert fs != null;
        int count = 0;
        for (File f : fs) {
            // 若非目录(即文件)  去掉.开头的隐藏文件
            if (!f.isDirectory() && !f.isHidden()) {
                String startName = f.getName();
                String spucode = startName.substring(0, 12);
                int suffixBegin = startName.lastIndexOf("_");
                String suffix = startName.substring(suffixBegin);
                String color = startName.substring(13, suffixBegin);
                int colorNum = getColornum(color, spucode);
                String realName = spucode + "_" + colorNum + suffix;
                System.out.println(realName);
                File newName = new File(removePath + "/" + realName);
                // 重命名并且移动
                if (!f.renameTo(newName)) {
                    System.out.println((startName + "移动失败"));
                }
            }
        }
    }

    public int getColornum(String colorIncheanese, String spucode) {
        switch (colorIncheanese) {
            case "白色系":
                return 1;
            case "灰色系":
                return 2;
            case "黑色系":
                return 3;
            case "红色系":
                return 4;
            case "橙色系":
                return 5;
            case "黄色系":
                return 6;
            case "绿色系":
                return 7;
            case "蓝色系":
                return 8;
            case "紫色系":
                return 9;
            case "粉色系":
                return 10;
            case "驼色系":
                return 11;
            case "焦糖色系":
                return 12;
            case "多色系":
                return 13;
            case "金色系":
                return 14;
            case "银色系":
                return 15;
            case "杏色系":
                return 16;
            case "白色_蓝色":
                return 17;
            case "白色_红色":
                return 18;
            case "白色_黑色":
                return 19;
            case "黑色_蓝色":
                return 20;
            case "藏蓝色系":
                return 21;
            case "浅牛仔蓝":
                return 22;
            case "深牛仔蓝":
                return 23;
            default:
                LOGGER.warn("+++_____+++ spucode为  {}  颜色有误,它的颜色为  {}", spucode, colorIncheanese);
                return 99;
        }
    }
}
