package spider.mglp.pojo;

public class ProductSpuWithBLOBs extends ProductSpu {

    private String detailsImages;

    private String spiderImgs;

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
}