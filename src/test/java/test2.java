import spider.mglp.util.SqlUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 * <p>
 * descirption:
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/18 上午11:39</pre>
 */
public class test2 {
    public static void main(String[] args) {
        HashSet<String> idSetOld = new HashSet<>();
        // 查询数据库，获取itemId——链接 数据

        // 查询数据库，获取所有的spu_code和taobao_link
        HashMap<String, String> spuIDMapOld = SqlUtils.getSpuCodeAndTbLinkOld();
        System.out.println(spuIDMapOld.size());
        for (Map.Entry<String, String> entry : spuIDMapOld.entrySet()) {
            // 从taobao_link获得itemId————————https://item.taobao.com/item.htm?id=568944784760&amp;amp;spm=2014.12440355.0.0
            int begin = entry.getValue().lastIndexOf("id=") + 3;
            String itemId = entry.getValue().substring(begin, begin + 12);
            idSetOld.add(itemId);
        }
        System.out.println(idSetOld.size() + "----");


        HashSet<String> idSetNew = new HashSet<>();
        // 查询数据库，获取itemId——链接 数据

        // 查询数据库，获取所有的spu_code和taobao_link
//        HashMap<String, String> spuIDMapNew = SqlUtils.getSpuCodeAndTbLink();
//        System.out.println(spuIDMapNew.size());
//        for (Map.Entry<String, String> entry : spuIDMapNew.entrySet()) {
//            // 从taobao_link获得itemId————————https://item.taobao.com/item.htm?id=568944784760&amp;amp;spm=2014.12440355.0.0
//            int begin = entry.getValue().lastIndexOf("id=") + 3;
//            String itemId = entry.getValue().substring(begin, begin + 12);
//            System.out.println("+++++++++++++     " + itemId);
//            idSetNew.add(itemId);
//        }
//        System.out.println(idSetNew.size() + "----");
//        idSetNew.removeAll(idSetOld);
//        for (String s : idSetNew){
//            System.out.println(s);
//        }
//        System.out.println(idSetNew.size());
        System.out.println("__________________________________++++++++++++++++++++++++++++++++++++");
        HashMap<String, String> spuIDMapNew = SqlUtils.getSpuCodeAndTbLink();
        System.out.println(spuIDMapNew.size());
        for (Map.Entry<String, String> entry : spuIDMapNew.entrySet()) {
            // 从taobao_link获得itemId————————https://item.taobao.com/item.htm?id=568944784760&amp;amp;spm=2014.12440355.0.0
            int begin = entry.getValue().lastIndexOf("id=") + 3;
            String itemId = entry.getValue().substring(begin, begin + 12);
            idSetNew.add(itemId);
        }
        System.out.println(idSetNew.size() + "----");
        for (String s : idSetNew){
            System.out.println(s);
        }
    }
}