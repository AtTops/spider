package spider.mglp.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import spider.mglp.pojo.ProductSpuWithBLOBs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * GetImgsUrlImpl Tester.
 *
 * @author <wang>
 * @version 1.0
 * @since <pre>07/11/2018</pre>
 */
public class GetImgsUrlImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getImgsUrlFromApi()
     */
    @Test
    public void testGetImgsUrlFromApi() throws Exception {
        String itemId = "571083893586";
        String pageNum = "2";
        String url = "https://rate.taobao.com/feedRateList.htm?auctionNumId=" + itemId + "&currentPageNum=" + pageNum;
        InputStream is = new URL(url).openStream();
        String jsonText;
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader brd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            sb = new StringBuilder();
            String cp;
            while ((cp = brd.readLine()) != null) {
                sb.append(cp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonText = sb.toString();
        jsonText = jsonText.substring(1, jsonText.length() - 1);
        // 无效的返回数据
        if (jsonText.length() < 5)
            System.out.println("===" + jsonText);
        ObjectMapper objMapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = objMapper.readTree(jsonText);
        } catch (JsonParseException e) {
            System.out.println(jsonText);
        }
        assert root != null;
        System.out.println("总数量：" + root.path("total").asInt());
        JsonNode commentsNode = root.path("comments");
        System.out.println("本页评论总数量：" + commentsNode.size());
        for (int i = 0; i < commentsNode.size(); i++) {
            JsonNode commentNow = commentsNode.get(i);
            // 获取到当前评论详情
            StringBuilder builder = new StringBuilder();
            builder.append(itemId).append(",").append(root.path("total").asInt()).append(",");
            // 初次评论时间
            String fCommentDate = commentNow.path("date").toString().replaceAll("\"", "");
            builder.append(fCommentDate).append(",");
            // 获取用户信息
            builder.append(commentNow.path("user").path("nick").toString().replaceAll("\"", "")).append(",").append(commentNow.path("user").path("vipLevel").toString()).append(",");
            // 获取sku信息
            builder.append(commentNow.path("auction").path("sku").toString().replaceAll("\"", "").replaceAll("&nbsp;&nbsp", "  ")).append(",");
            // 初次评论内容
            String fCommentValue = commentsNode.get(i).path("content").toString().replaceAll("\"", "").replaceAll(",", "，");
            //System.out.println(i + "===" + commentsNode.get(i));
            builder.append(fCommentValue).append(",");
            // 追加评论以及滞后时长
            JsonNode appendComment = commentNow.path("append");
            if (appendComment.toString().length() > 5) {
                builder.append(appendComment.path("dayAfterConfirm")).append(",").append(appendComment.path("content").toString().replaceAll("\"", ""));
            } else {
                builder.append(",");
            }
//            System.out.println("fCommentValue" + fCommentValue);
            System.out.println(builder.toString());

        }
        // images_url，这里大都只有5条数据，TODO：寻找其他地方出现的图片
//        if (root.findPath("images").toString().length() > 2) {
//            String[] images = root.findPath("images").toString().split(",");
//            int imagesCount = images.length;
//            StringBuilder stringBuilder = new StringBuilder();
//            for (int i = 0; i < imagesCount; i++) {
//                stringBuilder.append(images[i].replaceAll("[\\[\\]\"]", " ").trim().substring(2));
//                if (i != imagesCount - 1)
//                    stringBuilder.append(",");
//            }
//            // 如果确实有记录到链接，则给赋值数据库
//            if (stringBuilder.toString().trim().length() > 5) {
//                System.out.println(stringBuilder.toString());
//            }
//        }
    }
} 
