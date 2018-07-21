package spider.mglp.service.impl;

import spider.mglp.util.SqlUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 * <p>
 * descirption:
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/20 下午12:23</pre>
 */
public class TsetGetItemIdFromSpuCode {
    public static void main(String[] args) {
        HashMap<String, String> spuIDMap;
        Set<String> idSet = new HashSet<>();
        spuIDMap = SqlUtils.getSpuCodeAndTbLink();
        System.out.println(spuIDMap.size());

        Set<String> spuSet = null;

        // 读取文件spucode过滤

        File file = new File(
                "/Users/wanghai/Desktop/spucode_remain_20180720.txt");
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), "utf-8");
            if (file.isFile() && file.exists()) {

                spuSet = new HashSet<String>();
                BufferedReader br = new BufferedReader(read);
                String txt = null;

                // 读取文件，将文件内容放入到set中
                while ((txt = br.readLine()) != null) {
                    spuSet.add(txt);
                }
                br.close();
            }
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(spuSet.size());
        // 删除不要的spucode
        for (Iterator<Map.Entry<String, String>> it = spuIDMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = it.next();
            String spuCode = (String) entry.getKey();
            if (!spuSet.contains(spuCode)) {
                it.remove();
            }
        }
        for (Map.Entry<String, String> entry : spuIDMap.entrySet()) {
            // 从taobao_link获得itemId————————https://item.taobao.com/item.htm?id=568944784760&amp;amp;spm=2014.12440355.0.0
            int begin = entry.getValue().lastIndexOf("id=") + 3;
            String itemId = entry.getValue().substring(begin, begin + 12);
            idSet.add(itemId);
        }
        for (String s : idSet) {
            System.out.println(s);
        }
        // 验证通过，104 == 104，67个id
        System.out.println(spuIDMap.size());
        System.out.println(idSet.size());
        // 验证完全添加了urls
        int idCount = 0;
        HashMap<String, String> itemIdLinkMap = SqlUtils.getItemIdAndImgsUrl();
        for (Iterator<Map.Entry<String, String>> it = itemIdLinkMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = it.next();
            String itemId = (String) entry.getKey();
            if (!idSet.contains(itemId)) {
                it.remove();
            } else {
                idCount++;
            }
        }
        System.out.println("qiwang 67  " + idCount);
        HashSet<String> little = new HashSet<>();
        System.out.println("在 67 里面的有 " + itemIdLinkMap.size());
        for (Map.Entry<String, String> entry : itemIdLinkMap.entrySet()) {
            little.add(entry.getKey());
        }
        idSet.removeAll(little);
        for (String s : idSet){
            System.out.println(s);
        }
    }
}

