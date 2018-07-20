//package spider.mglp.service.impl;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import spider.mglp.enums.SqlEnum;
//import spider.mglp.pojo.SizeChart;
//import spider.mglp.util.SpiderHeader;
//import spider.mglp.util.SqlUtils;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * <p>pakage: spider.mglp.service.impl,descirption:</p>
// *
// * @author wanghai
// * @version V1.0
// * @since <pre>2018/7/15 下午9:54</pre>
// */
//public class SizeChartImpl implements spider.mglp.service.SizeChart {
//    private int size = 1;
//
//    @Override
//    public void downloadSizeChart() {
//        // 查询数据库，获取itemId——链接 数据
//        HashMap<String, String> itemIdLinkMap = SqlUtils.getItemIdAndImgsUrl();
//        // 查询数据库，获取所有的spu_code和taobao_link
//        HashMap<String, String> spuIDMap = SqlUtils.getSpuCodeAndTbLink();
//        System.out.println(spuIDMap.size());
//        ArrayList<SizeChart> records = new ArrayList<>(256);
//        for (Map.Entry<String, String> entry : spuIDMap.entrySet()) {
//            // 从taobao_link获得itemId————————https://item.taobao.com/item.htm?id=568944784760&amp;amp;spm=2014.12440355.0.0
//            int begin = entry.getValue().lastIndexOf("id=") + 3;
//            String itemId = entry.getValue().substring(begin, begin + 12);
//            // spuCode用于待会插入数据库
//            String spuCode = entry.getKey();
//            // 根据该itemId，查询itemIdLinkMap中的value值
//            if (itemIdLinkMap.containsKey(itemId)) {
//                size++;
//                SizeChart sizeChart = new SizeChart();
//                sizeChart.setSpuCode(spuCode);
//                System.out.println(itemId + "\t=\t" + itemIdLinkMap.get(itemId));
//                String url = itemIdLinkMap.get(itemId);
//                // 拿数据去
//                Document doc = null;
//                try {
//                    // 解决UnsupportedMimeTypeException，ignoreContentType(true)
//                    doc = Jsoup.connect(url).headers(SpiderHeader.headers).ignoreContentType(true).timeout(30000).get();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                assert doc != null : "没有获取到文档";
//                //System.out.println(doc.toString());
//                // 解析html，获得商品url
//                Elements links = doc.getElementsByTag("img");
//                StringBuilder stringBuilder = new StringBuilder();
//                for (Element e : links) {
//                    // 以该串开头的
//                    String regex = "^https://img.alicdn.com/imgextra/i.*";
//                    Pattern p = Pattern.compile(regex);
//                    String contents = e.attr("src");
//                    Matcher m = p.matcher(contents);
//                    while (m.find()) {
//                        stringBuilder.append(contents, m.start(), m.end());
//                        stringBuilder.append(",");
//                        //System.out.println(contents.substring(m.start(), m.end()));
//                    }
//                }
//                // 该itemID的imgs链接已经拿到，转为String,结合 SPU_code，存入数据库
//                productSpu.setSpiderImgsAll(stringBuilder.toString());
//                records.add(productSpu);
//            } else System.out.println("itemId\t" + itemId + "'s imgs_all_url is null\t" + itemId);
//            if (size % 100 == 0) {
//
//                SqlUtils.updateImgsUrlByBatch(SqlEnum.BATCH_UPDATE_IMGS_URL.getDesc(), records);
//                records.clear();
//            }
//        }
//        System.out.println("更新数量 : " + size + ",\t期望更新的总数量：" + (spuIDMap.size()));
//        // batch批量插入
//        SqlUtils.updateImgsUrlByBatch(SqlEnum.BATCH_UPDATE_IMGS_URL.getDesc(), records);
//    }
//}
