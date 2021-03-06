package spider.mglp.enums;

/**
 * <p>pakage: spider.mglp.enums,descirption:</p>
 *
 * product_spu_local相关的，已经不再使用了，相关表已经迁移到测试数据库，本地表已经不再使用
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/11 下午12:04</pre>
 */
public enum SqlEnum {
    BATCH_UPDATE_IMGS_URL("update product_spu_local set spider_imgs_all = ? where spu_code = ?", 1),
//    SPU_TLINK_SELL("select spu_code,taobao_link,season from product_spu_local where season like '%2%' and vendor_id = 28", 2),
    SPU_TLINK_SELL("select spu_code,taobao_link from product_spu where store_spu_code is not null and status = 1 and head_image !='' and LENGTH(taobao_link) > 40;", 2),
    SPU_TLINK_TIME("select spu_code,taobao_link from product_spu where c_time > ?", 3),
    SPU_TLINK_BY_SPUCODE("select spu_code,taobao_link from product_spu_local where spucode = ?", 4),
//    SELECT_SPU_IMG_URL("SELECT spu_code,spider_imgs_all from product_spu_local where length(spider_imgs_all) > 4 ", 3),
    SPU_UPLOADED("SELECT spu_code from product_spu_local where length(spider_imgs_all) > 4 and upload_flag = '1'", 5),
    SCODE_AND_IMGSURL_FLAG("SELECT spu_code,spider_imgs_all from product_spu_local where length(spider_imgs_all) > 4 and download_flag = ?", 6),
    SELECT_ITEMID_LINK("SELECT item_id,url from itemId_urls where url IS NOT NULL", 7),
    UPDATE_CDN_IMGS_ONLINE("update product_spu set head_image = ?,details_images = ? where spu_code = ?", 8),
    BACK_CDN_IMGS_LOCAL("update product_spu_local set head_image = ?,details_images = ? where spu_code = ?", 9),
    UPDATE_CDN_IMGS_LOCAL("update product_spu_local set head_image = ?,details_images = ?,upload_flag = '1' where spu_code = ?", 10),
    PREPARE_UPDATE_SPU("select head_image,details_images,spu_code from product_spu_local where download_flag > ? and upload_flag = '1'", 11),
    BACK_SPU_CODE("select spu_code from product_spu_local where details_images like '%20180719%' ", 12),
    BACK_DATA("select head_image,details_images,spu_code from product_spu where spu_code = ?", 13),
    SIZE_INFO("select DISTINCT(spu_code) from spu_size_info where type = ?", 14);

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
