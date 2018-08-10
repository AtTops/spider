package spider.mglp.service.impl;

/**
 * <p>pakage: spider.mglp.service.impl</p>
 * <p>
 * descirption:
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/6 下午1:15</pre>
 */
public class RegexTest {
    public static void main(String[] args) {
        String s = "STRIPE&nbsp;&llll;&nbsp尺码";
        System.out.println(s.replaceAll("&\\w+;|&\\w+", ""));
    }
}
