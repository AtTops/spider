package spider.mglp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spider.mglp.enums.UrlEnum;
import spider.mglp.service.DownloadImgs;
import etl.ReadThisTimeSpuCodeFile;
import spider.mglp.util.SqlUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>pakage: spider.mglp.service.impl,descirption:</p>
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/11 下午4:52</pre>
 */
public class DownloadImgsImpl implements DownloadImgs {
    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadImgsImpl.class);

    @Override
    public void downloadImgAndNamed(int flag, String savePath) throws IOException {
        // 获取所有的spu_code和其对应的spider_imgs_all(未下载的为0)
        int num = 0;
        HashMap<String, String> hashMap = SqlUtils.getSpuCodeAndImgsUrl(num);
        int count = 1;
        int error = 0;
        System.out.println(hashMap.size());
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String spuCode = entry.getKey();
            System.out.println(spuCode + "=====" + count++);
            // split imgs_url_all
            String[] imgs_url = entry.getValue().split(",");
            int nameFlag = 0;
            for (String s : imgs_url) {
//            int num = Math.min(10,imgs_url.length);
//            for (int i = 0; i < num; i++) {
//                String s = imgs_url[i];
                ++nameFlag;
                // download imgs
                // 避免 MalformedURLException: no protocol
                System.out.println("===" + s);
                URL url = null;
                try {
                    url = new URL(s);
                } catch (MalformedURLException e) {
                    LOGGER.error("error!!!" + spuCode + "=====" + ++error);
                    e.printStackTrace();
                }
                //打开网络输入流
                DataInputStream dis = null;
                try {
                    assert url != null;
                    dis = new DataInputStream(url.openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String newImageName = savePath + entry.getKey() + "_" + nameFlag + ".jpg";
                File filePic = new File(newImageName);

                //建立一个新的文件
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(filePic);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                byte[] buffer = new byte[10240];
                int length;
                //开始填充数据
                assert dis != null;
                while ((length = dis.read(buffer)) > 0) {
                    try {
                        assert fos != null;
                        fos.write(buffer, 0, length);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                dis.close();
                assert fos != null;
                fos.close();
            }
            // 该SPU_code下载完毕，更新数据库
            SqlUtils.setDownloaded(flag, spuCode);
            // 把这个save传入writeThisTimeDownloadedImgsSpu，写磁盘，追加
        }
    }

    public void writeThisTimeDownloadedImgsSpu(String savePath) throws IOException {
        Set<String> spuDownloaded = ReadThisTimeSpuCodeFile.countSpuFileLocal(savePath);
        String fileName = UrlEnum.SPU_DOWNLOADED_IMGS.getDesc() + "spu_downloaded.txt";
        FileWriter fileWriter;
        fileWriter = new FileWriter(new File(fileName),true);
        for (String spu : spuDownloaded) {
            fileWriter.write(spu);
            fileWriter.write("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public static void main(String[] args) {
        DownloadImgsImpl downloadImgs = new DownloadImgsImpl();
//        String savePath = "/Users/wanghai/IdeaProjects/img_20/";
//        // TODO: 每次更新 flag
//        try {
//            downloadImgs.downloadImgAndNamed(2, savePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String savePath = UrlEnum.FILES_DOWNLOADED_IMGS.getDesc() + "2018-07-20";
        try {
            downloadImgs.writeThisTimeDownloadedImgsSpu(savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
