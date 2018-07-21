//package spider.mglp.service.impl;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.mime.HttpMultipartMode;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import spider.mglp.util.SqlUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * <p>pakage: spider.mglp.service.impl</p>
// * <p>
// * descirption: 上传图片到cdn
// *
// * @author wanghai
// * @version V1.0
// * @since <pre>2018/7/18 下午5:20</pre>
// */
//public class UpdateImage_bak {
//    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateImage_bak.class);
//
//    public static void uploadImage(String dir) {
//        // 获取本地等待上传的所有图片
//        File files= new File(dir);
//        File[] fs = files.listFiles();
//        // 获取所有的spu_code和其对应的spider_imgs_all(下载了的，大于num)
//        HashMap<String, String> hashMap = SqlUtils.getSpuCodeAndImgsUrl(0);
//        System.out.println("预计 upload size: \t" + hashMap.size());
//        int deletedImgCount = 0;
//        int uploadedImgCount = 0;
////        HashMap<String, String> spuColor;
////        // 获取（spu，color）map
////        spuColor = SqlUtils.getSpuCodeAndColor();
//        // 遍历spu_code，获取本地是Spu_code_colorNum开头的图像
//        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
//            // 本次只上传该spu开头的图像
//            String spuCode = entry.getKey();
//            String coverPath = "wrong";
//            StringBuilder cdnImgsPath = new StringBuilder();
//            // 用来解决headImage
//            int thisCover = 0;
//            // 解决裁剪4：5
//            // boolean needcut = true;
//            assert fs != null;
//            for (File f : fs) {
//                // 若非目录(即文件)  去掉.开头的隐藏文件
//                if (!f.isDirectory() && !f.isHidden()) {
//                    String startName = f.getName();
//                    String spuThisPic = startName.substring(0, 12);
//                    if (spuThisPic.equals(spuCode)){
//                    // 执行上传逻辑
//
//
//
//
//
//
//
//
//
//
//
////            // 外层控制颜色
////            for (int i = 1; i < 24; i++) {
////                for (int j = 1; j < 24; j++) {
////                    String imgLocalPath = "/Users/wanghai/Downloads/to_upload/test/" + spuCode + "_" + i + ".jpg";
////                    // 如果存在文件，就上传，否则，日志记录
////                    File imgFile = new File(imgLocalPath);
////                    if (imgFile.exists()) {
////
////                        if (needcut) {
////                            // 裁剪4：5 TODO：
////                        }
////
////                        // 应该记录上传成功的第一张
////                        String thisCdnPath = UpdateImage.upload(imgLocalPath);
////                        // 这里不判断的话，如果status返回不是200，那么thisCdnPath会不在判断thisCdnPath长度时拒绝插入数据库，
////                        // 考虑到其他图像可能已经上传成功。此时数据库不会更新，就会有图像无法利用
////                        if (thisCdnPath.length() > 10) {
////                            // 返回的路径长度不异常，才正确上传了，且后面的图不再需要裁剪
////                            thisCover++;
////                            needcut = false;
////                            // 上传并且记录拼接cdn上的图片地址
////                            cdnImgsPath.append(thisCdnPath);
////                            cdnImgsPath.append(",");
////                        }
////                        if (thisCover == 1) {
////                            coverPath = thisCdnPath;
////                        }
////                    } else {
////                        deletedImgCount++;
////                        LOGGER.warn("no image {}", imgLocalPath);
////                    }
////                }
////            }
//            // for循环结束，该Spu_code结束上传
//            LOGGER.info("Spu_code \t {}\t 上传了 {} 张图", spuCode, thisCover);
//            uploadedImgCount += thisCover;
//            // 更新本地数据库，spu_code，cdnImgsPath，coverPath
//            if (coverPath.length() < 10) {
//                LOGGER.warn("不合法的coverImg:\t{} \t{}", coverPath, spuCode);
//            } else {
//                String imgs = cdnImgsPath.toString();
//                // 更新本地库
//                SqlUtils.updateOrBackUpLocalCdnImgs(coverPath, imgs.substring(0, imgs.length() - 1), spuCode, "update");
//            }
//        }
//        LOGGER.info("上传之前删除的图像有\t{}\t张", deletedImgCount);
//        LOGGER.info("上传成功的图像有\t{}\t张", uploadedImgCount);
//    }
//
//    /**
//     * 上传图片，post
//     *
//     * @param uploadImg 上传的图片的本地地址
//     * @return cdn图片地址
//     */
//    public static String upload(String uploadImg) {
//        String urlStr = "http://aladdin.yuncloset.com/api/file/uploadImage";
//        String cdnPath = "wrong";
//
//        File imageFile = new File(uploadImg);
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MzE5MDE1NzEsIm5iZiI6MTUzMTkwMTU3MSwic3ViIjo0LCJleHAiOjE1MzI1OTI3NzF9.9aj_3DeNlwfS9TcZPja1UgxDQ3kkQnYkSBkasR4d5pU";
//
//        HttpPost post = new HttpPost(urlStr);
//        HttpClient httpClient = new DefaultHttpClient();
//        FileBody fileBody = new FileBody(imageFile, ContentType.MULTIPART_FORM_DATA);
//        StringBody stringBody1 = new StringBody(token, ContentType.DEFAULT_TEXT);
////
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//        builder.addPart("image", fileBody);
//        builder.addPart("token", stringBody1);
//        HttpEntity entity = builder.build();
////
//        post.setEntity(entity);
//        HttpResponse response;
//        String back = "";
//        try {
//            response = httpClient.execute(post);
//            /**请求发送成功，并得到响应**/
//            if (response.getStatusLine().getStatusCode() == 200) {
//                try {
//
//                    /*读取服务器返回过来的json字符串数据**/
//
//                    back = EntityUtils.toString(response.getEntity());
//                    // json parse
//                    ObjectMapper mapper = new ObjectMapper();
//                    JsonNode rootNode = mapper.readTree(back); // 读取Json
//                    // rootNode.path("xx")返回的还是一个JsonNode对象，调用该JsonNode的相应方法，得到键对应的值
//                    JsonNode dataNode = rootNode.path("data");
//                    cdnPath = dataNode.path("image_url").asText();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                LOGGER.warn("img upload wrong\t {}", uploadImg);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return cdnPath;
//    }
//
//    public static HashMap<String, Integer> getFileNumPerSpu(String path) {
//        HashMap<String, Integer> map = new HashMap<>();
//        File files = new File(path);        //获取其file对象
//        File[] fs = files.listFiles();    //遍历path下的文件和目录，放在File数组中
//        assert fs != null;
//        int count = 0;
//        for (File f : fs) {
//            // 若非目录(即文件)  去掉.开头的隐藏文件
//            if (!f.isDirectory() && !f.isHidden()) {
//                String startName = f.getName();
//                String spucode = startName.substring(0, 12);
//                if (!map.containsKey(spucode)) {
//                    map.put(spucode, 1);
//                } else {
//                    map.put(spucode, map.get(spucode) + 1);
//                }
//            }
//        }
//        return map;
//    }
//
//    public static void main(String[] args) {
////        String path = "/Users/wanghai/Downloads/to_upload/test";
////        System.out.println(UpdateImage.upload(path));
////        UpdateImage.uploadImage();
////        HashMapUpdateImage.getFileNumPerSpu("/Users/wanghai/Downloads/to_upload/test");
//    }
//}