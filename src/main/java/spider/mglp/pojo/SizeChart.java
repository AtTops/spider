package spider.mglp.pojo;

public class SizeChart {
    private String spuCode;

    private String itemId;

    private Integer bust;

    private Integer waistline;

    private Integer clothLength;

    private Integer shoulderWidth;

    private Integer frontClothLength;

    private Integer backClothLength;

    private String size;

    private Integer hipCircum;

    private Integer pantLength;

    public String getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(String spuCode) {
        this.spuCode = spuCode == null ? null : spuCode.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public Integer getBust() {
        return bust;
    }

    public void setBust(Integer bust) {
        this.bust = bust;
    }

    public Integer getWaistline() {
        return waistline;
    }

    public void setWaistline(Integer waistline) {
        this.waistline = waistline;
    }

    public Integer getClothLength() {
        return clothLength;
    }

    public void setClothLength(Integer clothLength) {
        this.clothLength = clothLength;
    }

    public Integer getShoulderWidth() {
        return shoulderWidth;
    }

    public void setShoulderWidth(Integer shoulderWidth) {
        this.shoulderWidth = shoulderWidth;
    }

    public Integer getFrontClothLength() {
        return frontClothLength;
    }

    public void setFrontClothLength(Integer frontClothLength) {
        this.frontClothLength = frontClothLength;
    }

    public Integer getBackClothLength() {
        return backClothLength;
    }

    public void setBackClothLength(Integer backClothLength) {
        this.backClothLength = backClothLength;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public Integer getHipCircum() {
        return hipCircum;
    }

    public void setHipCircum(Integer hipCircum) {
        this.hipCircum = hipCircum;
    }

    public Integer getPantLength() {
        return pantLength;
    }

    public void setPantLength(Integer pantLength) {
        this.pantLength = pantLength;
    }
}