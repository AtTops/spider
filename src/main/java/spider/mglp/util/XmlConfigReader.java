package spider.mglp.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author 王海
 * @version V1.0
 * @Description 采用单列模式 ， 解析sys-config.xml文件
 */
public class XmlConfigReader {
    // 一个获取并设置jdbc配置信息的jdbcConfig全局变量
    // 聚合：事物A由若干个事物B组成，体现在类与类之间的关系就是：“类JdbcConfig的实例”作为“类XmlConfigReader”的“成员对象”出现。
    private JdbcConfig jdbcConfig = new JdbcConfig();
    // 懒汉式（使用时才创建实例，节省空间）
    private static XmlConfigReader instance = null; // 全局变量使得这个唯一实例能被访问
    // 构造函数变为私有:保证这个类只被实例化一次，且无法继承
    private XmlConfigReader(){
        //String path = "sys-config.xml";
        SAXReader reader = new SAXReader();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("sys-config.xml");
        try {
            Document doc =  reader.read(is);
            // 获取jdbc相关配置信息
            Element driverNameElt = (Element)doc.selectObject("/config/db-info/driver-name");
            Element urlElt = (Element) doc.selectObject("/config/db-info/url");
            Element adminNameElt = (Element) doc.selectObject("/config/db-info/user-name");
            Element passwordElt = (Element) doc.selectObject("/config/db-info/password");
            // 设置jdbc相关信息
            jdbcConfig.setDriverName(driverNameElt.getStringValue());
            jdbcConfig.setUrlValue(urlElt.getStringValue());
            jdbcConfig.setUserNameValue(adminNameElt.getStringValue());
            jdbcConfig.setPasswordValue(passwordElt.getStringValue());
        } catch (Exception e) {
            System.out.println("读取xml文件流出错");
            e.printStackTrace();
        }
    }
    // 其他类想实例化一个对象时调这个公有方法
    public static synchronized XmlConfigReader getInstance(){
        if (instance == null){
            instance = new XmlConfigReader();
        }
        return instance;
    }
    /**
     * @return 返回jdbc相关信息
     */
    public JdbcConfig getJdbcConfig(){
        return jdbcConfig;
    }

    /*public static void main(String[] args) {
        // 仅有的一个XmlConfigReader的实例（instance）调用getJdbcConfig
        JdbcConfig jdbcConfig = XmlConfigReader.getInstance().getJdbcConfig();
        System.out.println(jdbcConfig);
    }*/
}