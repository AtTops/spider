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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 * <p>
 * descirption:获取所有的评价信息
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/3 下午5:01</pre>
 */
public class RateInfo {
    private static final Logger LOGGER = LoggerFactory.getLogger(RateInfo.class);

    public void getAllRateInfo() throws IOException {
        // 查询数据库，获取所有的spu_code和taobao_link
        HashMap<String, String> spuIDMap = SqlUtils.getSpuCodeAndTbLink();

        // 该文件存放所有已经获取了信息的spu，itemId，或者没有获取到的，比如说下架啥的
        File getOrSout = new File(UrlEnum.BASIC_OUTFILE_PATH.getDesc() + "/rate/getorsout.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getOrSout, true));

        File rateInfo = new File(UrlEnum.BASIC_OUTFILE_PATH.getDesc() + "/rate/all_rate.csv");
        BufferedWriter buffWriter = new BufferedWriter(new FileWriter(rateInfo, true));

        Set<String> spidered = spidered("/Users/Shared/rate/all_rate.csv");
        for (Map.Entry<String, String> entry : spuIDMap.entrySet()) {
            int begin = entry.getValue().lastIndexOf("id=") + 3;
            String itemId = entry.getValue().substring(begin, begin + 12);
            String spuCode = entry.getKey();

            if (spidered.contains(spuCode)){
                continue;
            }
            String urlPrefix = "https://rate.taobao.com/feedRateList.htm?auctionNumId=" + itemId + "&currentPageNum=";
            // 首先获取第一页信息，然后获取之后的页面的评论信息
            int pages = getRateInfo(itemId, spuCode, urlPrefix, 1, bufferedWriter, buffWriter);
            if (pages > 1) {
                for (int page = 2; page <= pages; page++) {
                    int pagesInside = getRateInfo(itemId, spuCode, urlPrefix, page, bufferedWriter, buffWriter);
                    if (pagesInside == -1){
                        // 后面的留言都已经被店家删除
                        bufferedWriter.write(spuCode + "," + itemId + "," + page + ",after, deleted \n");
                        break;
                    }
                }
            }
        }
        bufferedWriter.flush();
        buffWriter.flush();
        bufferedWriter.close();
        buffWriter.close();
    }
    // 已经爬取过了的spucode
    public Set<String> spidered(String path){
        Set<String> spuSet = null;

        // 读取文件spucode过滤

        File file = new File(path);
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), "utf-8");
            if (file.isFile() && file.exists()) {
                spuSet = new HashSet<>();
                BufferedReader br = new BufferedReader(read);
                String txt;
                // 读取文件，将文件内容放入到set中
                while ((txt = br.readLine()) != null) {
                    spuSet.add(txt.substring(0,12));
                }
                br.close();
            }
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert spuSet != null;
        System.out.println("spuSet size:  "+spuSet.size());
        return spuSet;
    }

    public int getRateInfo(String itemId, String spuCode, String urlPrefix, int pageNo, BufferedWriter bufferedWriter, BufferedWriter buffWriter) throws IOException {
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

        assert sb != null;
        jsonText = sb.toString();
        // 裁剪前后的括号
        jsonText = jsonText.substring(1, jsonText.length() - 1);
        // 无效的返回数据. 没有获取到的，比如说下架、留言删除啥的
        if (jsonText.length() < 500) {
            System.out.println("===" + jsonText);
            // 写进文件备份
            bufferedWriter.write(spuCode + "," + itemId + "," + pageNo + ",can not get rate info\n");
            bufferedWriter.flush();
            return -1;
        }
        ObjectMapper objMapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = objMapper.readTree(jsonText);
        } catch (JsonParseException e) {
            System.out.println(jsonText);
        }
        assert root != null;
        int total = root.path("total").asInt();
        if (total < 1) {
            // 写进文件备份
            bufferedWriter.write(spuCode + "," + itemId + "," + pageNo + ",not rate info\n");
            bufferedWriter.flush();
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
        int pages = (int) Math.ceil(total / 20);
        if (pages == pageNo) {
            // 信息获取完毕，写入已获取评论的文件
            bufferedWriter.write(spuCode + "," + itemId + "\n");
            bufferedWriter.flush();
        }
        return pages;
    }

    public static void main(String[] args) throws IOException {
        RateInfo rateInfo = new RateInfo();
        rateInfo.getAllRateInfo();
//        rateInfo.spidered("/Users/Shared/rate/all_rate.csv");
    }
}