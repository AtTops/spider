package spider.mglp.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spider.mglp.util.ReadThisTimeSpuCodeFile;
import spider.mglp.util.SqlUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 * <p>
 * descirption: 上传图片到cdn
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/18 下午5:20</pre>
 */
public class UpdateImage {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateImage.class);

    public static void uploadImage(String dir) {
        // 获取本地等待上传的所有图片
        File files = new File(dir);
        File[] fs = files.listFiles();
        // 获取已经上传了的spu_code(upload_flag为'1' 过滤)
        HashSet<String> hashSetUploded = SqlUtils.getUploadedSpu();
        // 获取所有本次需要上传的spu_code
        Set<String> hashSet = ReadThisTimeSpuCodeFile.countSpuFileLocal("/Users/wanghai/Downloads/to_upload/0");
        hashSet.removeAll(hashSetUploded);
        System.out.println("本次预计上传总数（spu）:\t" + hashSet.size());
        int uploadedImgCount = 0;

        // 遍历spu_code，获取本地是Spu_code_colorNum开头的图像
        for (String spuCode : hashSet) {
            // 本次只上传该spu开头的图像
            String coverPath = "init";
            StringBuilder cdnImgsPath = new StringBuilder();
            // 用来解决headImage
            int thisCover = 0;
            // 解决裁剪4：5
            // boolean needcut = true;
            assert fs != null;
            for (File f : fs) {
                // 若非目录(即文件)  去掉.开头的隐藏文件
                if (!f.isDirectory() && !f.isHidden()) {
                    String picName = f.getName();
                    String spuThisPic = picName.substring(0, 12);
                    if (spuThisPic.equals(spuCode)) {
                        // 执行上传逻辑
                        // 应该记录上传成功的第一张
                        String thisCdnPath = UpdateImage.upload(f);
                        // 这里不判断的话，如果status返回不是200，那么thisCdnPath会不在判断thisCdnPath长度时拒绝插入数据库，
                        // 考虑到其他图像可能已经上传成功。此时数据库不会更新，就会有图像无法利用
                        if (thisCdnPath.length() > 10) {
                            LOGGER.info(picName + "=========" + thisCdnPath);
                            // 返回的路径长度不异常，才正确上传了
                            thisCover++;
                            // 上传并且记录拼接cdn上的图片地址
                            if (!(thisCover == 1)) {
                                cdnImgsPath.append(thisCdnPath);
                                cdnImgsPath.append(",");
                            }
                        }
                        if (thisCover == 1) {
                            coverPath = thisCdnPath;
                        }
                        System.out.println(picName);
                    }
                }
            }
            // for循环结束，该Spu_code结束上传
            LOGGER.info("spu:\t{}\t上传了 {} 张图", spuCode, thisCover);
            uploadedImgCount += thisCover;
            // 更新本地数据库，spu_code，cdnImgsPath，coverPath
            if (coverPath.length() < 10) {
                LOGGER.error("head_image: {},spu:{}", coverPath, spuCode);
            } else {
                String imgs = cdnImgsPath.toString();
                // 更新本地库
                SqlUtils.updateOrBackUpLocalCdnImgs(coverPath, imgs.substring(0, imgs.length() - 1), spuCode, "update");
            }
        }
        LOGGER.info("上传成功的图像有\t{}\t张", uploadedImgCount);
    }

    /**
     * 上传图片，post
     *
     * @param imageFile 上传的图片文件
     * @return cdn图片地址
     */
    public static String upload(File imageFile) {
        String urlStr = "http://aladdin.yuncloset.com/api/file/uploadImage";
        String cdnPath = "sthwrong";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MzIxNDc2MTYsIm5iZiI6MTUzMjE0NzYxNiwic3ViIjo0LCJleHAiOjE1MzI4Mzg4MTZ9.4D-yBi7ZVTl9deoU86H2ck2nO5ULdCDLSwBHyb53X74◊";

        HttpPost post = new HttpPost(urlStr);
        HttpClient httpClient = new DefaultHttpClient();
        FileBody fileBody = new FileBody(imageFile, ContentType.MULTIPART_FORM_DATA);
        StringBody stringBody1 = new StringBody(token, ContentType.DEFAULT_TEXT);
//
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addPart("image", fileBody);
        builder.addPart("token", stringBody1);
        HttpEntity entity = builder.build();
//
        post.setEntity(entity);
        HttpResponse response;
        String back;
        try {
            response = httpClient.execute(post);
            /*读取服务器返回过来的json字符串数据**/

            back = EntityUtils.toString(response.getEntity());
            // json parse
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(back); // 读取Json
            int statusCode = response.getStatusLine().getStatusCode();
            /* 请求发送成功，并得到响应 */
            if (statusCode == 200) {
                try {
                    // rootNode.path("xx")返回的还是一个JsonNode对象，调用该JsonNode的相应方法，得到键对应的值
                    JsonNode dataNode = rootNode.path("data");
                    cdnPath = dataNode.path("image_url").asText();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                LOGGER.error("status_code: {}",statusCode);
                LOGGER.error("img :{} upload wrong,  message: {},  ", imageFile.getName(), rootNode.path("message"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cdnPath;
    }

    public static void main(String[] args) {
        String path = "/Users/wanghai/Downloads/to_upload/0";
//        System.out.println(UpdateImage.upload(path));
        UpdateImage.uploadImage(path);
//        HashMapUpdateImage.getFileNumPerSpu("/Users/wanghai/Downloads/to_upload/test");
    }
}
