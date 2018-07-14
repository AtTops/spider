package spider.mglp.pojo;

import java.util.Date;

public class ProductSpu {
    private Integer id;

    private String spuCode;

    private String spuUid;

    private String brandStyle;

    private String title;

    private String fabricComposition;

    private Integer fabricType;

    private String season;

    private String styleId;

    private Integer brandId;

    private Integer vendorId;

    private Integer maxClassId;

    private Integer classId;

    private Integer price;

    private Integer salesPrice;

    private String priceCat;

    private String color;

    private String ppeId;

    private String image;

    private String taobaoLink;

    private String taobaoImage;

    private String storeSpuCode;

    private String brandColor;

    private Byte status;

    private Date cTime;

    private Date ts;

    private Boolean ifDownload;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(String spuCode) {
        this.spuCode = spuCode == null ? null : spuCode.trim();
    }

    public String getSpuUid() {
        return spuUid;
    }

    public void setSpuUid(String spuUid) {
        this.spuUid = spuUid == null ? null : spuUid.trim();
    }

    public String getBrandStyle() {
        return brandStyle;
    }

    public void setBrandStyle(String brandStyle) {
        this.brandStyle = brandStyle == null ? null : brandStyle.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFabricComposition() {
        return fabricComposition;
    }

    public void setFabricComposition(String fabricComposition) {
        this.fabricComposition = fabricComposition == null ? null : fabricComposition.trim();
    }

    public Integer getFabricType() {
        return fabricType;
    }

    public void setFabricType(Integer fabricType) {
        this.fabricType = fabricType;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season == null ? null : season.trim();
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId == null ? null : styleId.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getMaxClassId() {
        return maxClassId;
    }

    public void setMaxClassId(Integer maxClassId) {
        this.maxClassId = maxClassId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Integer salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getPriceCat() {
        return priceCat;
    }

    public void setPriceCat(String priceCat) {
        this.priceCat = priceCat == null ? null : priceCat.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getPpeId() {
        return ppeId;
    }

    public void setPpeId(String ppeId) {
        this.ppeId = ppeId == null ? null : ppeId.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getTaobaoLink() {
        return taobaoLink;
    }

    public void setTaobaoLink(String taobaoLink) {
        this.taobaoLink = taobaoLink == null ? null : taobaoLink.trim();
    }

    public String getTaobaoImage() {
        return taobaoImage;
    }

    public void setTaobaoImage(String taobaoImage) {
        this.taobaoImage = taobaoImage == null ? null : taobaoImage.trim();
    }

    public String getStoreSpuCode() {
        return storeSpuCode;
    }

    public void setStoreSpuCode(String storeSpuCode) {
        this.storeSpuCode = storeSpuCode == null ? null : storeSpuCode.trim();
    }

    public String getBrandColor() {
        return brandColor;
    }

    public void setBrandColor(String brandColor) {
        this.brandColor = brandColor == null ? null : brandColor.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Boolean getIfDownload() {
        return ifDownload;
    }

    public void setIfDownload(Boolean ifDownload) {
        this.ifDownload = ifDownload;
    }
}