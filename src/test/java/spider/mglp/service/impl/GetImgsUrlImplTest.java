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
        String url = "https://h5api.m.taobao.com/h5/mtop.taobao.detail.getdetail/6.0/?data=%7B%22itemNumId%22%3A%22569082944413%22%2C%22%7D";
        InputStream is = new URL(url).openStream();
        String jsonText;
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader brd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            sb = new StringBuilder();
            int cp;
            while ((cp = brd.read()) != -1) {
                sb.append((char) cp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
        jsonText = sb.toString();
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
        // images_url，这里大都只有5条数据，TODO：寻找其他地方出现的图片
        if (root.findPath("images").toString().length() > 2) {
            String[] images = root.findPath("images").toString().split(",");
            int imagesCount = images.length;
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < imagesCount; i++) {
                stringBuilder.append(images[i].replaceAll("[\\[\\]\"]", " ").trim().substring(2));
                if (i != imagesCount - 1)
                    stringBuilder.append(",");
            }
            // 如果确实有记录到链接，则给赋值数据库
            if (stringBuilder.toString().trim().length() > 5) {
                System.out.println(stringBuilder.toString());
            }
        }
    }
} 
