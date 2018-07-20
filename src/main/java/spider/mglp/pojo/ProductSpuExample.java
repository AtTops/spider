package spider.mglp.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductSpuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductSpuExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSpuCodeIsNull() {
            addCriterion("spu_code is null");
            return (Criteria) this;
        }

        public Criteria andSpuCodeIsNotNull() {
            addCriterion("spu_code is not null");
            return (Criteria) this;
        }

        public Criteria andSpuCodeEqualTo(String value) {
            addCriterion("spu_code =", value, "spuCode");
            return (Criteria) this;
        }

        public Criteria andSpuCodeNotEqualTo(String value) {
            addCriterion("spu_code <>", value, "spuCode");
            return (Criteria) this;
        }

        public Criteria andSpuCodeGreaterThan(String value) {
            addCriterion("spu_code >", value, "spuCode");
            return (Criteria) this;
        }

        public Criteria andSpuCodeGreaterThanOrEqualTo(String value) {
            addCriterion("spu_code >=", value, "spuCode");
            return (Criteria) this;
        }

        public Criteria andSpuCodeLessThan(String value) {
            addCriterion("spu_code <", value, "spuCode");
            return (Criteria) this;
        }

        public Criteria andSpuCodeLessThanOrEqualTo(String value) {
            addCriterion("spu_code <=", value, "spuCode");
            return (Criteria) this;
        }

        public Criteria andSpuCodeLike(String value) {
            addCriterion("spu_code like", value, "spuCode");
            return (Criteria) this;
        }

        public Criteria andSpuCodeNotLike(String value) {
            addCriterion("spu_code not like", value, "spuCode");
            return (Criteria) this;
        }

        public Criteria andSpuCodeIn(List<String> values) {
            addCriterion("spu_code in", values, "spuCode");
            return (Criteria) this;
        }

        public Criteria andSpuCodeNotIn(List<String> values) {
            addCriterion("spu_code not in", values, "spuCode");
            return (Criteria) this;
        }

        public Criteria andSpuCodeBetween(String value1, String value2) {
            addCriterion("spu_code between", value1, value2, "spuCode");
            return (Criteria) this;
        }

        public Criteria andSpuCodeNotBetween(String value1, String value2) {
            addCriterion("spu_code not between", value1, value2, "spuCode");
            return (Criteria) this;
        }

        public Criteria andSpuUidIsNull() {
            addCriterion("spu_uid is null");
            return (Criteria) this;
        }

        public Criteria andSpuUidIsNotNull() {
            addCriterion("spu_uid is not null");
            return (Criteria) this;
        }

        public Criteria andSpuUidEqualTo(String value) {
            addCriterion("spu_uid =", value, "spuUid");
            return (Criteria) this;
        }

        public Criteria andSpuUidNotEqualTo(String value) {
            addCriterion("spu_uid <>", value, "spuUid");
            return (Criteria) this;
        }

        public Criteria andSpuUidGreaterThan(String value) {
            addCriterion("spu_uid >", value, "spuUid");
            return (Criteria) this;
        }

        public Criteria andSpuUidGreaterThanOrEqualTo(String value) {
            addCriterion("spu_uid >=", value, "spuUid");
            return (Criteria) this;
        }

        public Criteria andSpuUidLessThan(String value) {
            addCriterion("spu_uid <", value, "spuUid");
            return (Criteria) this;
        }

        public Criteria andSpuUidLessThanOrEqualTo(String value) {
            addCriterion("spu_uid <=", value, "spuUid");
            return (Criteria) this;
        }

        public Criteria andSpuUidLike(String value) {
            addCriterion("spu_uid like", value, "spuUid");
            return (Criteria) this;
        }

        public Criteria andSpuUidNotLike(String value) {
            addCriterion("spu_uid not like", value, "spuUid");
            return (Criteria) this;
        }

        public Criteria andSpuUidIn(List<String> values) {
            addCriterion("spu_uid in", values, "spuUid");
            return (Criteria) this;
        }

        public Criteria andSpuUidNotIn(List<String> values) {
            addCriterion("spu_uid not in", values, "spuUid");
            return (Criteria) this;
        }

        public Criteria andSpuUidBetween(String value1, String value2) {
            addCriterion("spu_uid between", value1, value2, "spuUid");
            return (Criteria) this;
        }

        public Criteria andSpuUidNotBetween(String value1, String value2) {
            addCriterion("spu_uid not between", value1, value2, "spuUid");
            return (Criteria) this;
        }

        public Criteria andBrandStyleIsNull() {
            addCriterion("brand_style is null");
            return (Criteria) this;
        }

        public Criteria andBrandStyleIsNotNull() {
            addCriterion("brand_style is not null");
            return (Criteria) this;
        }

        public Criteria andBrandStyleEqualTo(String value) {
            addCriterion("brand_style =", value, "brandStyle");
            return (Criteria) this;
        }

        public Criteria andBrandStyleNotEqualTo(String value) {
            addCriterion("brand_style <>", value, "brandStyle");
            return (Criteria) this;
        }

        public Criteria andBrandStyleGreaterThan(String value) {
            addCriterion("brand_style >", value, "brandStyle");
            return (Criteria) this;
        }

        public Criteria andBrandStyleGreaterThanOrEqualTo(String value) {
            addCriterion("brand_style >=", value, "brandStyle");
            return (Criteria) this;
        }

        public Criteria andBrandStyleLessThan(String value) {
            addCriterion("brand_style <", value, "brandStyle");
            return (Criteria) this;
        }

        public Criteria andBrandStyleLessThanOrEqualTo(String value) {
            addCriterion("brand_style <=", value, "brandStyle");
            return (Criteria) this;
        }

        public Criteria andBrandStyleLike(String value) {
            addCriterion("brand_style like", value, "brandStyle");
            return (Criteria) this;
        }

        public Criteria andBrandStyleNotLike(String value) {
            addCriterion("brand_style not like", value, "brandStyle");
            return (Criteria) this;
        }

        public Criteria andBrandStyleIn(List<String> values) {
            addCriterion("brand_style in", values, "brandStyle");
            return (Criteria) this;
        }

        public Criteria andBrandStyleNotIn(List<String> values) {
            addCriterion("brand_style not in", values, "brandStyle");
            return (Criteria) this;
        }

        public Criteria andBrandStyleBetween(String value1, String value2) {
            addCriterion("brand_style between", value1, value2, "brandStyle");
            return (Criteria) this;
        }

        public Criteria andBrandStyleNotBetween(String value1, String value2) {
            addCriterion("brand_style not between", value1, value2, "brandStyle");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionIsNull() {
            addCriterion("fabric_composition is null");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionIsNotNull() {
            addCriterion("fabric_composition is not null");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionEqualTo(String value) {
            addCriterion("fabric_composition =", value, "fabricComposition");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionNotEqualTo(String value) {
            addCriterion("fabric_composition <>", value, "fabricComposition");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionGreaterThan(String value) {
            addCriterion("fabric_composition >", value, "fabricComposition");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionGreaterThanOrEqualTo(String value) {
            addCriterion("fabric_composition >=", value, "fabricComposition");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionLessThan(String value) {
            addCriterion("fabric_composition <", value, "fabricComposition");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionLessThanOrEqualTo(String value) {
            addCriterion("fabric_composition <=", value, "fabricComposition");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionLike(String value) {
            addCriterion("fabric_composition like", value, "fabricComposition");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionNotLike(String value) {
            addCriterion("fabric_composition not like", value, "fabricComposition");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionIn(List<String> values) {
            addCriterion("fabric_composition in", values, "fabricComposition");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionNotIn(List<String> values) {
            addCriterion("fabric_composition not in", values, "fabricComposition");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionBetween(String value1, String value2) {
            addCriterion("fabric_composition between", value1, value2, "fabricComposition");
            return (Criteria) this;
        }

        public Criteria andFabricCompositionNotBetween(String value1, String value2) {
            addCriterion("fabric_composition not between", value1, value2, "fabricComposition");
            return (Criteria) this;
        }

        public Criteria andFabricTypeIsNull() {
            addCriterion("fabric_type is null");
            return (Criteria) this;
        }

        public Criteria andFabricTypeIsNotNull() {
            addCriterion("fabric_type is not null");
            return (Criteria) this;
        }

        public Criteria andFabricTypeEqualTo(Integer value) {
            addCriterion("fabric_type =", value, "fabricType");
            return (Criteria) this;
        }

        public Criteria andFabricTypeNotEqualTo(Integer value) {
            addCriterion("fabric_type <>", value, "fabricType");
            return (Criteria) this;
        }

        public Criteria andFabricTypeGreaterThan(Integer value) {
            addCriterion("fabric_type >", value, "fabricType");
            return (Criteria) this;
        }

        public Criteria andFabricTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fabric_type >=", value, "fabricType");
            return (Criteria) this;
        }

        public Criteria andFabricTypeLessThan(Integer value) {
            addCriterion("fabric_type <", value, "fabricType");
            return (Criteria) this;
        }

        public Criteria andFabricTypeLessThanOrEqualTo(Integer value) {
            addCriterion("fabric_type <=", value, "fabricType");
            return (Criteria) this;
        }

        public Criteria andFabricTypeIn(List<Integer> values) {
            addCriterion("fabric_type in", values, "fabricType");
            return (Criteria) this;
        }

        public Criteria andFabricTypeNotIn(List<Integer> values) {
            addCriterion("fabric_type not in", values, "fabricType");
            return (Criteria) this;
        }

        public Criteria andFabricTypeBetween(Integer value1, Integer value2) {
            addCriterion("fabric_type between", value1, value2, "fabricType");
            return (Criteria) this;
        }

        public Criteria andFabricTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("fabric_type not between", value1, value2, "fabricType");
            return (Criteria) this;
        }

        public Criteria andSeasonIsNull() {
            addCriterion("season is null");
            return (Criteria) this;
        }

        public Criteria andSeasonIsNotNull() {
            addCriterion("season is not null");
            return (Criteria) this;
        }

        public Criteria andSeasonEqualTo(String value) {
            addCriterion("season =", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotEqualTo(String value) {
            addCriterion("season <>", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonGreaterThan(String value) {
            addCriterion("season >", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonGreaterThanOrEqualTo(String value) {
            addCriterion("season >=", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonLessThan(String value) {
            addCriterion("season <", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonLessThanOrEqualTo(String value) {
            addCriterion("season <=", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonLike(String value) {
            addCriterion("season like", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotLike(String value) {
            addCriterion("season not like", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonIn(List<String> values) {
            addCriterion("season in", values, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotIn(List<String> values) {
            addCriterion("season not in", values, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonBetween(String value1, String value2) {
            addCriterion("season between", value1, value2, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotBetween(String value1, String value2) {
            addCriterion("season not between", value1, value2, "season");
            return (Criteria) this;
        }

        public Criteria andStyleIdIsNull() {
            addCriterion("style_id is null");
            return (Criteria) this;
        }

        public Criteria andStyleIdIsNotNull() {
            addCriterion("style_id is not null");
            return (Criteria) this;
        }

        public Criteria andStyleIdEqualTo(String value) {
            addCriterion("style_id =", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdNotEqualTo(String value) {
            addCriterion("style_id <>", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdGreaterThan(String value) {
            addCriterion("style_id >", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdGreaterThanOrEqualTo(String value) {
            addCriterion("style_id >=", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdLessThan(String value) {
            addCriterion("style_id <", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdLessThanOrEqualTo(String value) {
            addCriterion("style_id <=", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdLike(String value) {
            addCriterion("style_id like", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdNotLike(String value) {
            addCriterion("style_id not like", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdIn(List<String> values) {
            addCriterion("style_id in", values, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdNotIn(List<String> values) {
            addCriterion("style_id not in", values, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdBetween(String value1, String value2) {
            addCriterion("style_id between", value1, value2, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdNotBetween(String value1, String value2) {
            addCriterion("style_id not between", value1, value2, "styleId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNull() {
            addCriterion("brand_id is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNotNull() {
            addCriterion("brand_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdEqualTo(Integer value) {
            addCriterion("brand_id =", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotEqualTo(Integer value) {
            addCriterion("brand_id <>", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThan(Integer value) {
            addCriterion("brand_id >", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("brand_id >=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThan(Integer value) {
            addCriterion("brand_id <", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThanOrEqualTo(Integer value) {
            addCriterion("brand_id <=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIn(List<Integer> values) {
            addCriterion("brand_id in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotIn(List<Integer> values) {
            addCriterion("brand_id not in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdBetween(Integer value1, Integer value2) {
            addCriterion("brand_id between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("brand_id not between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andVendorIdIsNull() {
            addCriterion("vendor_id is null");
            return (Criteria) this;
        }

        public Criteria andVendorIdIsNotNull() {
            addCriterion("vendor_id is not null");
            return (Criteria) this;
        }

        public Criteria andVendorIdEqualTo(Integer value) {
            addCriterion("vendor_id =", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotEqualTo(Integer value) {
            addCriterion("vendor_id <>", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdGreaterThan(Integer value) {
            addCriterion("vendor_id >", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vendor_id >=", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdLessThan(Integer value) {
            addCriterion("vendor_id <", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdLessThanOrEqualTo(Integer value) {
            addCriterion("vendor_id <=", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdIn(List<Integer> values) {
            addCriterion("vendor_id in", values, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotIn(List<Integer> values) {
            addCriterion("vendor_id not in", values, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdBetween(Integer value1, Integer value2) {
            addCriterion("vendor_id between", value1, value2, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vendor_id not between", value1, value2, "vendorId");
            return (Criteria) this;
        }

        public Criteria andMaxClassIdIsNull() {
            addCriterion("max_class_id is null");
            return (Criteria) this;
        }

        public Criteria andMaxClassIdIsNotNull() {
            addCriterion("max_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andMaxClassIdEqualTo(Integer value) {
            addCriterion("max_class_id =", value, "maxClassId");
            return (Criteria) this;
        }

        public Criteria andMaxClassIdNotEqualTo(Integer value) {
            addCriterion("max_class_id <>", value, "maxClassId");
            return (Criteria) this;
        }

        public Criteria andMaxClassIdGreaterThan(Integer value) {
            addCriterion("max_class_id >", value, "maxClassId");
            return (Criteria) this;
        }

        public Criteria andMaxClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_class_id >=", value, "maxClassId");
            return (Criteria) this;
        }

        public Criteria andMaxClassIdLessThan(Integer value) {
            addCriterion("max_class_id <", value, "maxClassId");
            return (Criteria) this;
        }

        public Criteria andMaxClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("max_class_id <=", value, "maxClassId");
            return (Criteria) this;
        }

        public Criteria andMaxClassIdIn(List<Integer> values) {
            addCriterion("max_class_id in", values, "maxClassId");
            return (Criteria) this;
        }

        public Criteria andMaxClassIdNotIn(List<Integer> values) {
            addCriterion("max_class_id not in", values, "maxClassId");
            return (Criteria) this;
        }

        public Criteria andMaxClassIdBetween(Integer value1, Integer value2) {
            addCriterion("max_class_id between", value1, value2, "maxClassId");
            return (Criteria) this;
        }

        public Criteria andMaxClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("max_class_id not between", value1, value2, "maxClassId");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("class_id is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(Integer value) {
            addCriterion("class_id =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(Integer value) {
            addCriterion("class_id <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(Integer value) {
            addCriterion("class_id >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_id >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(Integer value) {
            addCriterion("class_id <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("class_id <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<Integer> values) {
            addCriterion("class_id in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<Integer> values) {
            addCriterion("class_id not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(Integer value1, Integer value2) {
            addCriterion("class_id between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("class_id not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Integer value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Integer value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Integer value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Integer value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Integer value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Integer> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Integer> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Integer value1, Integer value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andSalesPriceIsNull() {
            addCriterion("sales_price is null");
            return (Criteria) this;
        }

        public Criteria andSalesPriceIsNotNull() {
            addCriterion("sales_price is not null");
            return (Criteria) this;
        }

        public Criteria andSalesPriceEqualTo(Integer value) {
            addCriterion("sales_price =", value, "salesPrice");
            return (Criteria) this;
        }

        public Criteria andSalesPriceNotEqualTo(Integer value) {
            addCriterion("sales_price <>", value, "salesPrice");
            return (Criteria) this;
        }

        public Criteria andSalesPriceGreaterThan(Integer value) {
            addCriterion("sales_price >", value, "salesPrice");
            return (Criteria) this;
        }

        public Criteria andSalesPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales_price >=", value, "salesPrice");
            return (Criteria) this;
        }

        public Criteria andSalesPriceLessThan(Integer value) {
            addCriterion("sales_price <", value, "salesPrice");
            return (Criteria) this;
        }

        public Criteria andSalesPriceLessThanOrEqualTo(Integer value) {
            addCriterion("sales_price <=", value, "salesPrice");
            return (Criteria) this;
        }

        public Criteria andSalesPriceIn(List<Integer> values) {
            addCriterion("sales_price in", values, "salesPrice");
            return (Criteria) this;
        }

        public Criteria andSalesPriceNotIn(List<Integer> values) {
            addCriterion("sales_price not in", values, "salesPrice");
            return (Criteria) this;
        }

        public Criteria andSalesPriceBetween(Integer value1, Integer value2) {
            addCriterion("sales_price between", value1, value2, "salesPrice");
            return (Criteria) this;
        }

        public Criteria andSalesPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("sales_price not between", value1, value2, "salesPrice");
            return (Criteria) this;
        }

        public Criteria andPriceCatIsNull() {
            addCriterion("price_cat is null");
            return (Criteria) this;
        }

        public Criteria andPriceCatIsNotNull() {
            addCriterion("price_cat is not null");
            return (Criteria) this;
        }

        public Criteria andPriceCatEqualTo(String value) {
            addCriterion("price_cat =", value, "priceCat");
            return (Criteria) this;
        }

        public Criteria andPriceCatNotEqualTo(String value) {
            addCriterion("price_cat <>", value, "priceCat");
            return (Criteria) this;
        }

        public Criteria andPriceCatGreaterThan(String value) {
            addCriterion("price_cat >", value, "priceCat");
            return (Criteria) this;
        }

        public Criteria andPriceCatGreaterThanOrEqualTo(String value) {
            addCriterion("price_cat >=", value, "priceCat");
            return (Criteria) this;
        }

        public Criteria andPriceCatLessThan(String value) {
            addCriterion("price_cat <", value, "priceCat");
            return (Criteria) this;
        }

        public Criteria andPriceCatLessThanOrEqualTo(String value) {
            addCriterion("price_cat <=", value, "priceCat");
            return (Criteria) this;
        }

        public Criteria andPriceCatLike(String value) {
            addCriterion("price_cat like", value, "priceCat");
            return (Criteria) this;
        }

        public Criteria andPriceCatNotLike(String value) {
            addCriterion("price_cat not like", value, "priceCat");
            return (Criteria) this;
        }

        public Criteria andPriceCatIn(List<String> values) {
            addCriterion("price_cat in", values, "priceCat");
            return (Criteria) this;
        }

        public Criteria andPriceCatNotIn(List<String> values) {
            addCriterion("price_cat not in", values, "priceCat");
            return (Criteria) this;
        }

        public Criteria andPriceCatBetween(String value1, String value2) {
            addCriterion("price_cat between", value1, value2, "priceCat");
            return (Criteria) this;
        }

        public Criteria andPriceCatNotBetween(String value1, String value2) {
            addCriterion("price_cat not between", value1, value2, "priceCat");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addCriterion("color =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addCriterion("color <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addCriterion("color >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addCriterion("color >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addCriterion("color <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addCriterion("color <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLike(String value) {
            addCriterion("color like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotLike(String value) {
            addCriterion("color not like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addCriterion("color in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addCriterion("color not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addCriterion("color between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addCriterion("color not between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andPpeIdIsNull() {
            addCriterion("ppe_id is null");
            return (Criteria) this;
        }

        public Criteria andPpeIdIsNotNull() {
            addCriterion("ppe_id is not null");
            return (Criteria) this;
        }

        public Criteria andPpeIdEqualTo(String value) {
            addCriterion("ppe_id =", value, "ppeId");
            return (Criteria) this;
        }

        public Criteria andPpeIdNotEqualTo(String value) {
            addCriterion("ppe_id <>", value, "ppeId");
            return (Criteria) this;
        }

        public Criteria andPpeIdGreaterThan(String value) {
            addCriterion("ppe_id >", value, "ppeId");
            return (Criteria) this;
        }

        public Criteria andPpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ppe_id >=", value, "ppeId");
            return (Criteria) this;
        }

        public Criteria andPpeIdLessThan(String value) {
            addCriterion("ppe_id <", value, "ppeId");
            return (Criteria) this;
        }

        public Criteria andPpeIdLessThanOrEqualTo(String value) {
            addCriterion("ppe_id <=", value, "ppeId");
            return (Criteria) this;
        }

        public Criteria andPpeIdLike(String value) {
            addCriterion("ppe_id like", value, "ppeId");
            return (Criteria) this;
        }

        public Criteria andPpeIdNotLike(String value) {
            addCriterion("ppe_id not like", value, "ppeId");
            return (Criteria) this;
        }

        public Criteria andPpeIdIn(List<String> values) {
            addCriterion("ppe_id in", values, "ppeId");
            return (Criteria) this;
        }

        public Criteria andPpeIdNotIn(List<String> values) {
            addCriterion("ppe_id not in", values, "ppeId");
            return (Criteria) this;
        }

        public Criteria andPpeIdBetween(String value1, String value2) {
            addCriterion("ppe_id between", value1, value2, "ppeId");
            return (Criteria) this;
        }

        public Criteria andPpeIdNotBetween(String value1, String value2) {
            addCriterion("ppe_id not between", value1, value2, "ppeId");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkIsNull() {
            addCriterion("taobao_link is null");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkIsNotNull() {
            addCriterion("taobao_link is not null");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkEqualTo(String value) {
            addCriterion("taobao_link =", value, "taobaoLink");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkNotEqualTo(String value) {
            addCriterion("taobao_link <>", value, "taobaoLink");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkGreaterThan(String value) {
            addCriterion("taobao_link >", value, "taobaoLink");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkGreaterThanOrEqualTo(String value) {
            addCriterion("taobao_link >=", value, "taobaoLink");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkLessThan(String value) {
            addCriterion("taobao_link <", value, "taobaoLink");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkLessThanOrEqualTo(String value) {
            addCriterion("taobao_link <=", value, "taobaoLink");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkLike(String value) {
            addCriterion("taobao_link like", value, "taobaoLink");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkNotLike(String value) {
            addCriterion("taobao_link not like", value, "taobaoLink");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkIn(List<String> values) {
            addCriterion("taobao_link in", values, "taobaoLink");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkNotIn(List<String> values) {
            addCriterion("taobao_link not in", values, "taobaoLink");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkBetween(String value1, String value2) {
            addCriterion("taobao_link between", value1, value2, "taobaoLink");
            return (Criteria) this;
        }

        public Criteria andTaobaoLinkNotBetween(String value1, String value2) {
            addCriterion("taobao_link not between", value1, value2, "taobaoLink");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageIsNull() {
            addCriterion("taobao_image is null");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageIsNotNull() {
            addCriterion("taobao_image is not null");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageEqualTo(String value) {
            addCriterion("taobao_image =", value, "taobaoImage");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageNotEqualTo(String value) {
            addCriterion("taobao_image <>", value, "taobaoImage");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageGreaterThan(String value) {
            addCriterion("taobao_image >", value, "taobaoImage");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageGreaterThanOrEqualTo(String value) {
            addCriterion("taobao_image >=", value, "taobaoImage");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageLessThan(String value) {
            addCriterion("taobao_image <", value, "taobaoImage");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageLessThanOrEqualTo(String value) {
            addCriterion("taobao_image <=", value, "taobaoImage");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageLike(String value) {
            addCriterion("taobao_image like", value, "taobaoImage");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageNotLike(String value) {
            addCriterion("taobao_image not like", value, "taobaoImage");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageIn(List<String> values) {
            addCriterion("taobao_image in", values, "taobaoImage");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageNotIn(List<String> values) {
            addCriterion("taobao_image not in", values, "taobaoImage");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageBetween(String value1, String value2) {
            addCriterion("taobao_image between", value1, value2, "taobaoImage");
            return (Criteria) this;
        }

        public Criteria andTaobaoImageNotBetween(String value1, String value2) {
            addCriterion("taobao_image not between", value1, value2, "taobaoImage");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeIsNull() {
            addCriterion("store_spu_code is null");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeIsNotNull() {
            addCriterion("store_spu_code is not null");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeEqualTo(String value) {
            addCriterion("store_spu_code =", value, "storeSpuCode");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeNotEqualTo(String value) {
            addCriterion("store_spu_code <>", value, "storeSpuCode");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeGreaterThan(String value) {
            addCriterion("store_spu_code >", value, "storeSpuCode");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeGreaterThanOrEqualTo(String value) {
            addCriterion("store_spu_code >=", value, "storeSpuCode");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeLessThan(String value) {
            addCriterion("store_spu_code <", value, "storeSpuCode");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeLessThanOrEqualTo(String value) {
            addCriterion("store_spu_code <=", value, "storeSpuCode");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeLike(String value) {
            addCriterion("store_spu_code like", value, "storeSpuCode");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeNotLike(String value) {
            addCriterion("store_spu_code not like", value, "storeSpuCode");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeIn(List<String> values) {
            addCriterion("store_spu_code in", values, "storeSpuCode");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeNotIn(List<String> values) {
            addCriterion("store_spu_code not in", values, "storeSpuCode");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeBetween(String value1, String value2) {
            addCriterion("store_spu_code between", value1, value2, "storeSpuCode");
            return (Criteria) this;
        }

        public Criteria andStoreSpuCodeNotBetween(String value1, String value2) {
            addCriterion("store_spu_code not between", value1, value2, "storeSpuCode");
            return (Criteria) this;
        }

        public Criteria andBrandColorIsNull() {
            addCriterion("brand_color is null");
            return (Criteria) this;
        }

        public Criteria andBrandColorIsNotNull() {
            addCriterion("brand_color is not null");
            return (Criteria) this;
        }

        public Criteria andBrandColorEqualTo(String value) {
            addCriterion("brand_color =", value, "brandColor");
            return (Criteria) this;
        }

        public Criteria andBrandColorNotEqualTo(String value) {
            addCriterion("brand_color <>", value, "brandColor");
            return (Criteria) this;
        }

        public Criteria andBrandColorGreaterThan(String value) {
            addCriterion("brand_color >", value, "brandColor");
            return (Criteria) this;
        }

        public Criteria andBrandColorGreaterThanOrEqualTo(String value) {
            addCriterion("brand_color >=", value, "brandColor");
            return (Criteria) this;
        }

        public Criteria andBrandColorLessThan(String value) {
            addCriterion("brand_color <", value, "brandColor");
            return (Criteria) this;
        }

        public Criteria andBrandColorLessThanOrEqualTo(String value) {
            addCriterion("brand_color <=", value, "brandColor");
            return (Criteria) this;
        }

        public Criteria andBrandColorLike(String value) {
            addCriterion("brand_color like", value, "brandColor");
            return (Criteria) this;
        }

        public Criteria andBrandColorNotLike(String value) {
            addCriterion("brand_color not like", value, "brandColor");
            return (Criteria) this;
        }

        public Criteria andBrandColorIn(List<String> values) {
            addCriterion("brand_color in", values, "brandColor");
            return (Criteria) this;
        }

        public Criteria andBrandColorNotIn(List<String> values) {
            addCriterion("brand_color not in", values, "brandColor");
            return (Criteria) this;
        }

        public Criteria andBrandColorBetween(String value1, String value2) {
            addCriterion("brand_color between", value1, value2, "brandColor");
            return (Criteria) this;
        }

        public Criteria andBrandColorNotBetween(String value1, String value2) {
            addCriterion("brand_color not between", value1, value2, "brandColor");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCTimeIsNull() {
            addCriterion("c_time is null");
            return (Criteria) this;
        }

        public Criteria andCTimeIsNotNull() {
            addCriterion("c_time is not null");
            return (Criteria) this;
        }

        public Criteria andCTimeEqualTo(Date value) {
            addCriterion("c_time =", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeNotEqualTo(Date value) {
            addCriterion("c_time <>", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeGreaterThan(Date value) {
            addCriterion("c_time >", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("c_time >=", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeLessThan(Date value) {
            addCriterion("c_time <", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeLessThanOrEqualTo(Date value) {
            addCriterion("c_time <=", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeIn(List<Date> values) {
            addCriterion("c_time in", values, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeNotIn(List<Date> values) {
            addCriterion("c_time not in", values, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeBetween(Date value1, Date value2) {
            addCriterion("c_time between", value1, value2, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeNotBetween(Date value1, Date value2) {
            addCriterion("c_time not between", value1, value2, "cTime");
            return (Criteria) this;
        }

        public Criteria andTsIsNull() {
            addCriterion("ts is null");
            return (Criteria) this;
        }

        public Criteria andTsIsNotNull() {
            addCriterion("ts is not null");
            return (Criteria) this;
        }

        public Criteria andTsEqualTo(Date value) {
            addCriterion("ts =", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotEqualTo(Date value) {
            addCriterion("ts <>", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThan(Date value) {
            addCriterion("ts >", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThanOrEqualTo(Date value) {
            addCriterion("ts >=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThan(Date value) {
            addCriterion("ts <", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThanOrEqualTo(Date value) {
            addCriterion("ts <=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsIn(List<Date> values) {
            addCriterion("ts in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotIn(List<Date> values) {
            addCriterion("ts not in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsBetween(Date value1, Date value2) {
            addCriterion("ts between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotBetween(Date value1, Date value2) {
            addCriterion("ts not between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andIfDownloadIsNull() {
            addCriterion("download_flag is null");
            return (Criteria) this;
        }

        public Criteria andIfDownloadIsNotNull() {
            addCriterion("download_flag is not null");
            return (Criteria) this;
        }

        public Criteria andIfDownloadEqualTo(Boolean value) {
            addCriterion("download_flag =", value, "ifDownload");
            return (Criteria) this;
        }

        public Criteria andIfDownloadNotEqualTo(Boolean value) {
            addCriterion("download_flag <>", value, "ifDownload");
            return (Criteria) this;
        }

        public Criteria andIfDownloadGreaterThan(Boolean value) {
            addCriterion("download_flag >", value, "ifDownload");
            return (Criteria) this;
        }

        public Criteria andIfDownloadGreaterThanOrEqualTo(Boolean value) {
            addCriterion("download_flag >=", value, "ifDownload");
            return (Criteria) this;
        }

        public Criteria andIfDownloadLessThan(Boolean value) {
            addCriterion("download_flag <", value, "ifDownload");
            return (Criteria) this;
        }

        public Criteria andIfDownloadLessThanOrEqualTo(Boolean value) {
            addCriterion("download_flag <=", value, "ifDownload");
            return (Criteria) this;
        }

        public Criteria andIfDownloadIn(List<Boolean> values) {
            addCriterion("download_flag in", values, "ifDownload");
            return (Criteria) this;
        }

        public Criteria andIfDownloadNotIn(List<Boolean> values) {
            addCriterion("download_flag not in", values, "ifDownload");
            return (Criteria) this;
        }

        public Criteria andIfDownloadBetween(Boolean value1, Boolean value2) {
            addCriterion("download_flag between", value1, value2, "ifDownload");
            return (Criteria) this;
        }

        public Criteria andIfDownloadNotBetween(Boolean value1, Boolean value2) {
            addCriterion("download_flag not between", value1, value2, "ifDownload");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}