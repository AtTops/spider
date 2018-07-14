package spider.mglp.pojo;

public class ProductSpuWithBLOBs extends ProductSpu {
    private String detailsImages;

    private String spiderImgs;

    private String spiderImgsAll;

    public String getDetailsImages() {
        return detailsImages;
    }

    public void setDetailsImages(String detailsImages) {
        this.detailsImages = detailsImages == null ? null : detailsImages.trim();
    }

    public String getSpiderImgs() {
        return spiderImgs;
    }

    public void setSpiderImgs(String spiderImgs) {
        this.spiderImgs = spiderImgs == null ? null : spiderImgs.trim();
    }

    public String getSpiderImgsAll() {
        return spiderImgsAll;
    }

    public void setSpiderImgsAll(String spiderImgsAll) {
        this.spiderImgsAll = spiderImgsAll == null ? null : spiderImgsAll.trim();
    }
}