package spider.mglp.util;

/**
 * @author: 王海
 * @version: V1.0
 * @Description:
 * @Package: per.wangh.util
 */
public class JdbcConfig {
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    private String driverName;
    private String urlValue;
    private String adminNameValue;
    private String passwordValue;

    public String getUrlValue() {
        return urlValue;
    }

    public void setUrlValue(String urlValue) {
        this.urlValue = urlValue;
    }

    public String getUserNameValue() {
        return adminNameValue;
    }

    public void setUserNameValue(String adminNameValue) {
        this.adminNameValue = adminNameValue;
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }
    @Override
    public String toString(){
        return this.getClass().getName() + "{ URL是：" + urlValue + "\n" + "USERNAME为：" + adminNameValue + "pw:" + passwordValue + "}";
    }
}