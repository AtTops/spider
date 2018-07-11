package spider.mglp.util;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>pakage: spider.mglp.util,descirption:</p>
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/9 下午7:28</pre>
 */
public class SpiderHeader {
    public static Map<String, String> headers;

    static {
        headers = new HashMap<String, String>();
        headers.put("User-Agent", "MMozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        headers.put("accept", "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01");
        headers.put("accept-language", "accept-language: zh-CN,zh;q=0.9");
        headers.put("accept-encoding", "gzip, deflate, br");
        headers.put("Referer", "https://room-209.taobao.com/category.htm");
        headers.put("Connection", "keep-alive");
        headers.put("upgrade-insecure-requests", "1");
    }
}
