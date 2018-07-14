package spider.mglp.enums;

/**
 * <p>pakage: spider.mglp.enums,descirption:</p>
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/11 下午12:04</pre>
 */
public enum SqlEnum {
    BATCH_UPDATE_IMGS_URL("update product_spu set spider_imgs_all = ? where spu_code = ?", 1),
    SELECT_SPU_TLINK("select spu_code,taobao_link from product_spu", 2),
    SELECT_SPU_IMG_URL("SELECT spu_code,spider_imgs_all from product_spu where spider_imgs_all IS NOT NULL", 3),
    SELECT_ITEMID_LINK("SELECT item_id,url from itemId_urls where url IS NOT NULL", 4);

    private int type;
    private String desc;

    SqlEnum(String desc, int type) {
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
