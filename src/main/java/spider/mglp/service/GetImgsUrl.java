package spider.mglp.service;

import java.io.IOException;
import java.util.HashMap;

/**
 * <p>pakage: spider.mglp.service</p>
 *
 * descirption:
 * 根据商品id，获取imgs地址
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/9 下午4:19</pre>
 */
public interface GetImgsUrl {
    void getImgsUrlFromApi() throws IOException, InterruptedException;
}
