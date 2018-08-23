package spider.mglp.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spider.mglp.enums.UrlEnum;
import spider.mglp.util.SqlUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 *
 * descirption:获取所有的评价信息，考虑到我们更新spu的频率，每周五上午8点15分定时运行【15 8 * * 5】,已经打包在测试服务器运行
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/3 下午5:01</pre>
 */
public class RateInfo {
    private static final Logger LOGGER = LoggerFactory.getLogger(RateInfo.class);

    public void getAllRateInfo(String localDate) throws IOException {
        // 查询数据库，获取所有的spu_code和taobao_link
        HashMap<String, String> spuIDMap = SqlUtils.getSpuCodeAndTbLink(localDate);

        File rateInfo = new File(UrlEnum.RATE_PATH.getDesc() + localDate + "+7_rate.csv");
        BufferedWriter buffWriter = new BufferedWriter(new FileWriter(rateInfo, true));

        for (Map.Entry<String, String> entry : spuIDMap.entrySet()) {
            int begin = entry.getValue().lastIndexOf("id=") + 3;
            String itemId = entry.getValue().substring(begin, begin + 12);
            String spuCode = entry.getKey();
            String urlPrefix = "https://rate.taobao.com/feedRateList.htm?auctionNumId=" + itemId + "&currentPageNum=";
            // 首先获取第一页信息，然后获取之后的页面的评论信息
            int pages = getRateInfo(itemId, spuCode, urlPrefix, 1, buffWriter);
            if (pages > 1) {
                for (int page = 2; page <= pages; page++) {
                    int pagesInside = getRateInfo(itemId, spuCode, urlPrefix, page, buffWriter);
                    if (pagesInside == -1) {
                        // 后面的留言都已经被店家删除
                        System.out.println((spuCode + "," + itemId + "," + page + ",after, deleted \n"));
                        break;
                    }
                }
            }
        }
        buffWriter.flush();
        buffWriter.close();
    }

    public int getRateInfo(String itemId, String spuCode, String urlPrefix, int pageNo, BufferedWriter buffWriter) throws IOException {
        String url = urlPrefix + pageNo;
        String jsonText;
        StringBuilder sb = null;
        try (InputStream is = new URL(url).openStream(); BufferedReader brd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")))) {
            sb = new StringBuilder();
            String cp;
            while ((cp = brd.readLine()) != null) {
                sb.append(cp);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }

        if (sb == null) {
            return -1;
        }
        jsonText = sb.toString();
        // 裁剪前后的括号
        jsonText = jsonText.substring(1, jsonText.length() - 1);
        // 无效的返回数据. 没有获取到的，比如说下架、留言删除啥的
        if (jsonText.length() < 500) {
            System.out.println("===" + jsonText);
            // 写进文件备份
            System.out.println(spuCode + "," + itemId + "," + pageNo + ",can not get rate info\n");
            return -1;
        }
        ObjectMapper objMapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = objMapper.readTree(jsonText);
        } catch (JsonParseException e) {
            System.out.println(jsonText);
        }
        if (root == null) {
            return -1;
        }
        int total = root.path("total").asInt();
        if (total < 1) {
            // 写进文件备份
            System.out.println(spuCode + "," + itemId + "," + pageNo + ",not rate info\n");
            return -1;
        }
        JsonNode commentsNode = root.path("comments");
        //System.out.println("本页评论总数量：" + commentsNode.size());
        for (int i = 0; i < commentsNode.size(); i++) {
            JsonNode commentNow = commentsNode.get(i);
            // 获取到当前评论详情
            StringBuilder builder = new StringBuilder();
            builder.append(spuCode).append(",").append(itemId).append(",").append(root.path("total").asInt()).append(",");
            // 初次评论时间
            String fCommentDate = commentNow.path("date").toString().replaceAll("\"", "");
            builder.append(fCommentDate).append(",");
            // 获取用户信息
            builder.append(commentNow.path("user").path("nick").toString().replaceAll("\"", "")).append(",").append(commentNow.path("user").path("vipLevel").toString()).append(",");
            // 获取sku信息
            builder.append(commentNow.path("auction").path("sku").toString().replaceAll("\"|&\\w+;|&\\w+", "")).append(",");
            // 初次评论内容
            String fCommentValue = commentsNode.get(i).path("content").toString().replaceAll("\"|&\\w+;|&\\w+", "").replaceAll(",", "，");
            //System.out.println(i + "===" + commentsNode.get(i));
            builder.append(fCommentValue).append(",");
            // 追加评论以及滞后时长
            JsonNode appendComment = commentNow.path("append");
            if (appendComment.toString().length() > 5) {
                // 无追加评论时，返回是null
                builder.append(appendComment.path("dayAfterConfirm")).append(",").append(appendComment.path("content").toString().replaceAll("\"|&\\w+;|&\\w+", "").replaceAll(",", "，"));
            } else {
                builder.append(",");
            }
            buffWriter.write(builder.toString() + "\n");
        }
        return (int) Math.ceil(total / 20);
    }
}
