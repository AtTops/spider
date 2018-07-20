package spider.mglp.enums;

/**
 * <p>Descirption:</p>
 *
 * @author 王海
 * @version V1.0
 * @package spider.mglp.enums
 * @since api1.0
 */
public enum UrlEnum {

    BASIC_URL("https://room-209.taobao.com/i/asynSearch.htm?mid=w-18518582515-0&pageNo=", 1),
    API_PRIFIX("https://h5api.m.taobao.com/h5/mtop.taobao.detail.getdetail/6.0/?v=6.0&type=jsonp&data=%7B%22itemNumId%22%3A%22", 2),
    API_SUFFIX("%22%2C%22exParams%22%3A%22%7B%5C%22id%5C%22%3A%5C%22571597818260%5C%22%7D%22%2C%22detail_v%22%3A%223.1.1%22%2C%22ttid%22%3A%222018%40taobao_iphone_9.9.9%22%2C%22utdid%22%3A%22123123123123123%22%7D", 3),
    ITEM_URL("https://item.taobao.com/item.htmid=", 4);

    private int type;
    private String desc;

    UrlEnum(String desc, int type) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
