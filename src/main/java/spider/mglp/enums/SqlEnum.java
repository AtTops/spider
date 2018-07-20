package spider.mglp.enums;

/**
 * <p>pakage: spider.mglp.enums,descirption:</p>
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/11 下午12:04</pre>
 */
public enum SqlEnum {
    BATCH_UPDATE_IMGS_URL("update product_spu_local set spider_imgs_all = ? where spu_code = ?", 1),
//    SELECT_SPU_TLINK("select spu_code,taobao_link,season from product_spu_local where season like '%2%' and vendor_id = 28", 2),
    SELECT_SPU_TLINK("select spu_code,taobao_link,season from product_spu_local", 2),
    SELECT_SPU_TLINK_BY_SPUCODE("select spu_code,taobao_link,season from product_spu_local where spucode = ?", 3),
//    SELECT_SPU_IMG_URL("SELECT spu_code,spider_imgs_all from product_spu_local where length(spider_imgs_all) > 4 ", 3),
//    SCODE_AND_IMGSURL("SELECT spu_code,spider_imgs_all from product_spu_local where length(spider_imgs_all) > 4 and download_flag='0'", 4),
    SCODE_AND_IMGSURL_FLAG("SELECT spu_code,spider_imgs_all from product_spu_local where length(spider_imgs_all) > 4 and download_flag = ?", 5),
    SELECT_ITEMID_LINK("SELECT item_id,url from itemId_urls where url IS NOT NULL", 6),
    UPDATE_CDN_IMGS_ONLINE("update product_spu set image = ?,details_images = ? where spu_code = ?", 7),
    UPDATE_CDN_IMGS_LOCAL("update product_spu_local set image = ?,details_images = ? where spu_code = ?", 8),
    PREPARE_UPDATE_SPU("select image,details_images,spu_code from product_spu_local where download_flag = '1' and length(details_images) > 50", 9),
    BACK_SPU_CODE("select spu_code from product_spu_local where details_images like '%20180719%' ", 10),
    BACK_DATA("select image,details_images,spu_code from product_spu where spu_code = ?", 11);

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
