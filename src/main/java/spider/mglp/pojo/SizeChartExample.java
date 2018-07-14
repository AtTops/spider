package spider.mglp.pojo;

import java.util.ArrayList;
import java.util.List;

public class SizeChartExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SizeChartExample() {
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

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("item_id like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("item_id not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andBustIsNull() {
            addCriterion("bust is null");
            return (Criteria) this;
        }

        public Criteria andBustIsNotNull() {
            addCriterion("bust is not null");
            return (Criteria) this;
        }

        public Criteria andBustEqualTo(Integer value) {
            addCriterion("bust =", value, "bust");
            return (Criteria) this;
        }

        public Criteria andBustNotEqualTo(Integer value) {
            addCriterion("bust <>", value, "bust");
            return (Criteria) this;
        }

        public Criteria andBustGreaterThan(Integer value) {
            addCriterion("bust >", value, "bust");
            return (Criteria) this;
        }

        public Criteria andBustGreaterThanOrEqualTo(Integer value) {
            addCriterion("bust >=", value, "bust");
            return (Criteria) this;
        }

        public Criteria andBustLessThan(Integer value) {
            addCriterion("bust <", value, "bust");
            return (Criteria) this;
        }

        public Criteria andBustLessThanOrEqualTo(Integer value) {
            addCriterion("bust <=", value, "bust");
            return (Criteria) this;
        }

        public Criteria andBustIn(List<Integer> values) {
            addCriterion("bust in", values, "bust");
            return (Criteria) this;
        }

        public Criteria andBustNotIn(List<Integer> values) {
            addCriterion("bust not in", values, "bust");
            return (Criteria) this;
        }

        public Criteria andBustBetween(Integer value1, Integer value2) {
            addCriterion("bust between", value1, value2, "bust");
            return (Criteria) this;
        }

        public Criteria andBustNotBetween(Integer value1, Integer value2) {
            addCriterion("bust not between", value1, value2, "bust");
            return (Criteria) this;
        }

        public Criteria andWaistlineIsNull() {
            addCriterion("waistline is null");
            return (Criteria) this;
        }

        public Criteria andWaistlineIsNotNull() {
            addCriterion("waistline is not null");
            return (Criteria) this;
        }

        public Criteria andWaistlineEqualTo(Integer value) {
            addCriterion("waistline =", value, "waistline");
            return (Criteria) this;
        }

        public Criteria andWaistlineNotEqualTo(Integer value) {
            addCriterion("waistline <>", value, "waistline");
            return (Criteria) this;
        }

        public Criteria andWaistlineGreaterThan(Integer value) {
            addCriterion("waistline >", value, "waistline");
            return (Criteria) this;
        }

        public Criteria andWaistlineGreaterThanOrEqualTo(Integer value) {
            addCriterion("waistline >=", value, "waistline");
            return (Criteria) this;
        }

        public Criteria andWaistlineLessThan(Integer value) {
            addCriterion("waistline <", value, "waistline");
            return (Criteria) this;
        }

        public Criteria andWaistlineLessThanOrEqualTo(Integer value) {
            addCriterion("waistline <=", value, "waistline");
            return (Criteria) this;
        }

        public Criteria andWaistlineIn(List<Integer> values) {
            addCriterion("waistline in", values, "waistline");
            return (Criteria) this;
        }

        public Criteria andWaistlineNotIn(List<Integer> values) {
            addCriterion("waistline not in", values, "waistline");
            return (Criteria) this;
        }

        public Criteria andWaistlineBetween(Integer value1, Integer value2) {
            addCriterion("waistline between", value1, value2, "waistline");
            return (Criteria) this;
        }

        public Criteria andWaistlineNotBetween(Integer value1, Integer value2) {
            addCriterion("waistline not between", value1, value2, "waistline");
            return (Criteria) this;
        }

        public Criteria andClothLengthIsNull() {
            addCriterion("cloth_length is null");
            return (Criteria) this;
        }

        public Criteria andClothLengthIsNotNull() {
            addCriterion("cloth_length is not null");
            return (Criteria) this;
        }

        public Criteria andClothLengthEqualTo(Integer value) {
            addCriterion("cloth_length =", value, "clothLength");
            return (Criteria) this;
        }

        public Criteria andClothLengthNotEqualTo(Integer value) {
            addCriterion("cloth_length <>", value, "clothLength");
            return (Criteria) this;
        }

        public Criteria andClothLengthGreaterThan(Integer value) {
            addCriterion("cloth_length >", value, "clothLength");
            return (Criteria) this;
        }

        public Criteria andClothLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("cloth_length >=", value, "clothLength");
            return (Criteria) this;
        }

        public Criteria andClothLengthLessThan(Integer value) {
            addCriterion("cloth_length <", value, "clothLength");
            return (Criteria) this;
        }

        public Criteria andClothLengthLessThanOrEqualTo(Integer value) {
            addCriterion("cloth_length <=", value, "clothLength");
            return (Criteria) this;
        }

        public Criteria andClothLengthIn(List<Integer> values) {
            addCriterion("cloth_length in", values, "clothLength");
            return (Criteria) this;
        }

        public Criteria andClothLengthNotIn(List<Integer> values) {
            addCriterion("cloth_length not in", values, "clothLength");
            return (Criteria) this;
        }

        public Criteria andClothLengthBetween(Integer value1, Integer value2) {
            addCriterion("cloth_length between", value1, value2, "clothLength");
            return (Criteria) this;
        }

        public Criteria andClothLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("cloth_length not between", value1, value2, "clothLength");
            return (Criteria) this;
        }

        public Criteria andShoulderWidthIsNull() {
            addCriterion("shoulder_width is null");
            return (Criteria) this;
        }

        public Criteria andShoulderWidthIsNotNull() {
            addCriterion("shoulder_width is not null");
            return (Criteria) this;
        }

        public Criteria andShoulderWidthEqualTo(Integer value) {
            addCriterion("shoulder_width =", value, "shoulderWidth");
            return (Criteria) this;
        }

        public Criteria andShoulderWidthNotEqualTo(Integer value) {
            addCriterion("shoulder_width <>", value, "shoulderWidth");
            return (Criteria) this;
        }

        public Criteria andShoulderWidthGreaterThan(Integer value) {
            addCriterion("shoulder_width >", value, "shoulderWidth");
            return (Criteria) this;
        }

        public Criteria andShoulderWidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("shoulder_width >=", value, "shoulderWidth");
            return (Criteria) this;
        }

        public Criteria andShoulderWidthLessThan(Integer value) {
            addCriterion("shoulder_width <", value, "shoulderWidth");
            return (Criteria) this;
        }

        public Criteria andShoulderWidthLessThanOrEqualTo(Integer value) {
            addCriterion("shoulder_width <=", value, "shoulderWidth");
            return (Criteria) this;
        }

        public Criteria andShoulderWidthIn(List<Integer> values) {
            addCriterion("shoulder_width in", values, "shoulderWidth");
            return (Criteria) this;
        }

        public Criteria andShoulderWidthNotIn(List<Integer> values) {
            addCriterion("shoulder_width not in", values, "shoulderWidth");
            return (Criteria) this;
        }

        public Criteria andShoulderWidthBetween(Integer value1, Integer value2) {
            addCriterion("shoulder_width between", value1, value2, "shoulderWidth");
            return (Criteria) this;
        }

        public Criteria andShoulderWidthNotBetween(Integer value1, Integer value2) {
            addCriterion("shoulder_width not between", value1, value2, "shoulderWidth");
            return (Criteria) this;
        }

        public Criteria andFrontClothLengthIsNull() {
            addCriterion("front_cloth_length is null");
            return (Criteria) this;
        }

        public Criteria andFrontClothLengthIsNotNull() {
            addCriterion("front_cloth_length is not null");
            return (Criteria) this;
        }

        public Criteria andFrontClothLengthEqualTo(Integer value) {
            addCriterion("front_cloth_length =", value, "frontClothLength");
            return (Criteria) this;
        }

        public Criteria andFrontClothLengthNotEqualTo(Integer value) {
            addCriterion("front_cloth_length <>", value, "frontClothLength");
            return (Criteria) this;
        }

        public Criteria andFrontClothLengthGreaterThan(Integer value) {
            addCriterion("front_cloth_length >", value, "frontClothLength");
            return (Criteria) this;
        }

        public Criteria andFrontClothLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("front_cloth_length >=", value, "frontClothLength");
            return (Criteria) this;
        }

        public Criteria andFrontClothLengthLessThan(Integer value) {
            addCriterion("front_cloth_length <", value, "frontClothLength");
            return (Criteria) this;
        }

        public Criteria andFrontClothLengthLessThanOrEqualTo(Integer value) {
            addCriterion("front_cloth_length <=", value, "frontClothLength");
            return (Criteria) this;
        }

        public Criteria andFrontClothLengthIn(List<Integer> values) {
            addCriterion("front_cloth_length in", values, "frontClothLength");
            return (Criteria) this;
        }

        public Criteria andFrontClothLengthNotIn(List<Integer> values) {
            addCriterion("front_cloth_length not in", values, "frontClothLength");
            return (Criteria) this;
        }

        public Criteria andFrontClothLengthBetween(Integer value1, Integer value2) {
            addCriterion("front_cloth_length between", value1, value2, "frontClothLength");
            return (Criteria) this;
        }

        public Criteria andFrontClothLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("front_cloth_length not between", value1, value2, "frontClothLength");
            return (Criteria) this;
        }

        public Criteria andBackClothLengthIsNull() {
            addCriterion("back_cloth_length is null");
            return (Criteria) this;
        }

        public Criteria andBackClothLengthIsNotNull() {
            addCriterion("back_cloth_length is not null");
            return (Criteria) this;
        }

        public Criteria andBackClothLengthEqualTo(Integer value) {
            addCriterion("back_cloth_length =", value, "backClothLength");
            return (Criteria) this;
        }

        public Criteria andBackClothLengthNotEqualTo(Integer value) {
            addCriterion("back_cloth_length <>", value, "backClothLength");
            return (Criteria) this;
        }

        public Criteria andBackClothLengthGreaterThan(Integer value) {
            addCriterion("back_cloth_length >", value, "backClothLength");
            return (Criteria) this;
        }

        public Criteria andBackClothLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("back_cloth_length >=", value, "backClothLength");
            return (Criteria) this;
        }

        public Criteria andBackClothLengthLessThan(Integer value) {
            addCriterion("back_cloth_length <", value, "backClothLength");
            return (Criteria) this;
        }

        public Criteria andBackClothLengthLessThanOrEqualTo(Integer value) {
            addCriterion("back_cloth_length <=", value, "backClothLength");
            return (Criteria) this;
        }

        public Criteria andBackClothLengthIn(List<Integer> values) {
            addCriterion("back_cloth_length in", values, "backClothLength");
            return (Criteria) this;
        }

        public Criteria andBackClothLengthNotIn(List<Integer> values) {
            addCriterion("back_cloth_length not in", values, "backClothLength");
            return (Criteria) this;
        }

        public Criteria andBackClothLengthBetween(Integer value1, Integer value2) {
            addCriterion("back_cloth_length between", value1, value2, "backClothLength");
            return (Criteria) this;
        }

        public Criteria andBackClothLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("back_cloth_length not between", value1, value2, "backClothLength");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(String value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(String value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(String value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(String value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(String value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(String value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLike(String value) {
            addCriterion("size like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotLike(String value) {
            addCriterion("size not like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<String> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<String> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(String value1, String value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(String value1, String value2) {
            addCriterion("size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andHipCircumIsNull() {
            addCriterion("hip_circum is null");
            return (Criteria) this;
        }

        public Criteria andHipCircumIsNotNull() {
            addCriterion("hip_circum is not null");
            return (Criteria) this;
        }

        public Criteria andHipCircumEqualTo(Integer value) {
            addCriterion("hip_circum =", value, "hipCircum");
            return (Criteria) this;
        }

        public Criteria andHipCircumNotEqualTo(Integer value) {
            addCriterion("hip_circum <>", value, "hipCircum");
            return (Criteria) this;
        }

        public Criteria andHipCircumGreaterThan(Integer value) {
            addCriterion("hip_circum >", value, "hipCircum");
            return (Criteria) this;
        }

        public Criteria andHipCircumGreaterThanOrEqualTo(Integer value) {
            addCriterion("hip_circum >=", value, "hipCircum");
            return (Criteria) this;
        }

        public Criteria andHipCircumLessThan(Integer value) {
            addCriterion("hip_circum <", value, "hipCircum");
            return (Criteria) this;
        }

        public Criteria andHipCircumLessThanOrEqualTo(Integer value) {
            addCriterion("hip_circum <=", value, "hipCircum");
            return (Criteria) this;
        }

        public Criteria andHipCircumIn(List<Integer> values) {
            addCriterion("hip_circum in", values, "hipCircum");
            return (Criteria) this;
        }

        public Criteria andHipCircumNotIn(List<Integer> values) {
            addCriterion("hip_circum not in", values, "hipCircum");
            return (Criteria) this;
        }

        public Criteria andHipCircumBetween(Integer value1, Integer value2) {
            addCriterion("hip_circum between", value1, value2, "hipCircum");
            return (Criteria) this;
        }

        public Criteria andHipCircumNotBetween(Integer value1, Integer value2) {
            addCriterion("hip_circum not between", value1, value2, "hipCircum");
            return (Criteria) this;
        }

        public Criteria andPantLengthIsNull() {
            addCriterion("pant_length is null");
            return (Criteria) this;
        }

        public Criteria andPantLengthIsNotNull() {
            addCriterion("pant_length is not null");
            return (Criteria) this;
        }

        public Criteria andPantLengthEqualTo(Integer value) {
            addCriterion("pant_length =", value, "pantLength");
            return (Criteria) this;
        }

        public Criteria andPantLengthNotEqualTo(Integer value) {
            addCriterion("pant_length <>", value, "pantLength");
            return (Criteria) this;
        }

        public Criteria andPantLengthGreaterThan(Integer value) {
            addCriterion("pant_length >", value, "pantLength");
            return (Criteria) this;
        }

        public Criteria andPantLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("pant_length >=", value, "pantLength");
            return (Criteria) this;
        }

        public Criteria andPantLengthLessThan(Integer value) {
            addCriterion("pant_length <", value, "pantLength");
            return (Criteria) this;
        }

        public Criteria andPantLengthLessThanOrEqualTo(Integer value) {
            addCriterion("pant_length <=", value, "pantLength");
            return (Criteria) this;
        }

        public Criteria andPantLengthIn(List<Integer> values) {
            addCriterion("pant_length in", values, "pantLength");
            return (Criteria) this;
        }

        public Criteria andPantLengthNotIn(List<Integer> values) {
            addCriterion("pant_length not in", values, "pantLength");
            return (Criteria) this;
        }

        public Criteria andPantLengthBetween(Integer value1, Integer value2) {
            addCriterion("pant_length between", value1, value2, "pantLength");
            return (Criteria) this;
        }

        public Criteria andPantLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("pant_length not between", value1, value2, "pantLength");
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