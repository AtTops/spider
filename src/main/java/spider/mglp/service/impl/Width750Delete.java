package spider.mglp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 * <p>
 * descirption:
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/19 下午7:20</pre>
 */
public class Width750Delete {
    private static final Logger LOGGER = LoggerFactory.getLogger(Width750Delete.class);

    public static void main(String[] args) throws IOException {
        // 要遍历的路径
        String path = "/Users/wanghai/images/img_20";
        // 删除的图片的存储新路径
        String newpath = "/Users/wanghai/shendeng_back/deleted_img/20180720";
        File dir = new File(path);
        File[] fs = dir.listFiles();
        assert fs != null;
        int count = 0;
        for (File picture : fs) {
            if (!picture.isHidden()) {
                BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
                int width = 0;
                try {
                    width = sourceImg.getWidth();
                } catch (NullPointerException nulle) {
                    System.out.println(picture.getName());
                    nulle.printStackTrace();
                    break;
                }
                // 源图宽度
                if (width < 750) {
                    String name = picture.getName();
                    File newName = new File(newpath + "/" + name);
                    if (!picture.renameTo(newName)) {
                        LOGGER.warn(name + "移动失败");
                    } else {
                        LOGGER.info("移动\t{} ,  图片宽度：{} pixel ,  图片大小：{} KB", name, width, sourceImg.getHeight());
                        count++;
                    }
                }
            }
        }
        LOGGER.info("共移动   {} 张宽度小于750 px 的图片", count);
    }
}
