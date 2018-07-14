package spider.mglp.service.impl;

import spider.mglp.enums.SqlEnum;
import spider.mglp.service.DownloadImgs;
import spider.mglp.util.SqlUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>pakage: spider.mglp.service.impl,descirption:</p>
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/11 下午4:52</pre>
 */
public class DownloadImgsImpl implements DownloadImgs {
    @Override
    public void downloadImgAndNamed() throws IOException {
        // 获取所有的spu_code和其对应的spider_imgs_all
        HashMap<String, String> hashMap = SqlUtils.getSpuCodeAndImgsUrl();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey());
            // split imgs_url_all
            String[] imgs_url = entry.getValue().split(",");
            int nameFlag = 0;
            for (String s : imgs_url) {
                ++nameFlag;
                // download imgs
                // 避免 MalformedURLException: no protocol
                System.out.println("===" + s);
                URL url = new URL(s);
                //打开网络输入流
                DataInputStream dis = new DataInputStream(url.openStream());
                String newImageName = "/Users/wanghai/IdeaProjects/detail_imgs_all/" + entry.getKey() + "_" + nameFlag + ".jpg";
                //建立一个新的文件
                FileOutputStream fos = new FileOutputStream(new File(newImageName));
                byte[] buffer = new byte[2048];
                int length;
                //开始填充数据
                while ((length = dis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                dis.close();
                fos.close();
            }
        }
    }
}
