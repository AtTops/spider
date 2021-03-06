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
    // 从接口拿图像的url前缀
    API_PRIFIX("https://h5api.m.taobao.com/h5/mtop.taobao.detail.getdetail/6.0/?v=6.0&type=jsonp&data=%7B%22itemNumId%22%3A%22", 2),
    // 从接口拿图像的url后缀
    API_SUFFIX("%22%2C%22exParams%22%3A%22%7B%5C%22id%5C%22%3A%5C%22571597818260%5C%22%7D%22%2C%22detail_v%22%3A%223.1.1%22%2C%22ttid%22%3A%222018%40taobao_iphone_9.9.9%22%2C%22utdid%22%3A%22123123123123123%22%7D", 3),
    ITEM_URL("https://item.taobao.com/item.htmid=", 4),
    // 每日在架spu文件，每天只更新一次,以spu_日期命名
    SPU_EVERYDAY_PATH("/Users/wanghai/spider/spu_everyday/", 5),
    // 已经下载图片成功的SPU，追加
    SPU_DOWNLOADED_IMGS("/Users/wanghai/spider/img/spu/img_downloaded.txt", 6),
    // 下载下来的图片的存放路径，后面是日期目录，然后是img文件
    FILES_DOWNLOADED_IMGS("/Users/wanghai/spider/img/files/", 7),
    // 已经爬取尺码的SPU，追加
    SPU_SIZE_SUCCESS("/Users/wanghai/spider/size/spu/success/size_downloaded.txt", 8),
    // 已经爬取试穿的SPU，追加
    SPU_TRY_SUCCESS("/Users/wanghai/spider/try/spu/success/try_downloaded.txt", 9),
    // 需要人工添加链接，每日重写，不记录历史
    SPU_NEED_ADD_BYHAND("/Users/wanghai/spider/need_add_byhand.txt", 10),
    CSV_SIZE_SUCCESS("/Users/wanghai/spider/size/files/csv/", 11),
    CSV_TRY_SUCCESS("/Users/wanghai/spider/try/files/csv/", 12),
    // 尺码csv转为json的路径
    JSON_SIZE_SUCCESS_ADJUST("/Users/wanghai/spider/size/files/json/adjust/", 13),
    // 试穿csv转为json的路径(nochange)
    JSON_TRY_SUCCESS("/Users/wanghai/spider/try/files/json/original/", 14),
    // 调整为线上需求的json格式的存储路径
    JSON_TRY_SUCCESS_ADJUST("/Users/wanghai/spider/try/files/json/adjust/", 15),

    ALL_LABEL_FILE_JSON("/home/wanghai/spider/label/files/json/original/", 16),
    ADJUST_LABEL_FILE_JSON("/home/wanghai/spider/label/files/json/adjust/", 17),
    // try处理为json的中间状态（未处理体重形式），没什么用
    TRY_PATH_WRITE_TMP("/Users/wanghai/spider/tmp/try_tmp.json", 18),
    RATE_PATH("/home/wanghai/spider/rate/csv/original/", 19);

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
