package cn.fts.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FileExample() {
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

        public Criteria andFileidIsNull() {
            addCriterion("fileid is null");
            return (Criteria) this;
        }

        public Criteria andFileidIsNotNull() {
            addCriterion("fileid is not null");
            return (Criteria) this;
        }

        public Criteria andFileidEqualTo(String value) {
            addCriterion("fileid =", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidNotEqualTo(String value) {
            addCriterion("fileid <>", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidGreaterThan(String value) {
            addCriterion("fileid >", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidGreaterThanOrEqualTo(String value) {
            addCriterion("fileid >=", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidLessThan(String value) {
            addCriterion("fileid <", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidLessThanOrEqualTo(String value) {
            addCriterion("fileid <=", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidLike(String value) {
            addCriterion("fileid like", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidNotLike(String value) {
            addCriterion("fileid not like", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidIn(List<String> values) {
            addCriterion("fileid in", values, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidNotIn(List<String> values) {
            addCriterion("fileid not in", values, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidBetween(String value1, String value2) {
            addCriterion("fileid between", value1, value2, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidNotBetween(String value1, String value2) {
            addCriterion("fileid not between", value1, value2, "fileid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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

        public Criteria andSizeEqualTo(Long value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Long value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Long value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Long value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Long value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Long> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Long> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Long value1, Long value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Long value1, Long value2) {
            addCriterion("size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andStartIsNull() {
            addCriterion("start is null");
            return (Criteria) this;
        }

        public Criteria andStartIsNotNull() {
            addCriterion("start is not null");
            return (Criteria) this;
        }

        public Criteria andStartEqualTo(Date value) {
            addCriterion("start =", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotEqualTo(Date value) {
            addCriterion("start <>", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThan(Date value) {
            addCriterion("start >", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThanOrEqualTo(Date value) {
            addCriterion("start >=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThan(Date value) {
            addCriterion("start <", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThanOrEqualTo(Date value) {
            addCriterion("start <=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartIn(List<Date> values) {
            addCriterion("start in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotIn(List<Date> values) {
            addCriterion("start not in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartBetween(Date value1, Date value2) {
            addCriterion("start between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotBetween(Date value1, Date value2) {
            addCriterion("start not between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andKeepIsNull() {
            addCriterion("keep is null");
            return (Criteria) this;
        }

        public Criteria andKeepIsNotNull() {
            addCriterion("keep is not null");
            return (Criteria) this;
        }

        public Criteria andKeepEqualTo(Integer value) {
            addCriterion("keep =", value, "keep");
            return (Criteria) this;
        }

        public Criteria andKeepNotEqualTo(Integer value) {
            addCriterion("keep <>", value, "keep");
            return (Criteria) this;
        }

        public Criteria andKeepGreaterThan(Integer value) {
            addCriterion("keep >", value, "keep");
            return (Criteria) this;
        }

        public Criteria andKeepGreaterThanOrEqualTo(Integer value) {
            addCriterion("keep >=", value, "keep");
            return (Criteria) this;
        }

        public Criteria andKeepLessThan(Integer value) {
            addCriterion("keep <", value, "keep");
            return (Criteria) this;
        }

        public Criteria andKeepLessThanOrEqualTo(Integer value) {
            addCriterion("keep <=", value, "keep");
            return (Criteria) this;
        }

        public Criteria andKeepIn(List<Integer> values) {
            addCriterion("keep in", values, "keep");
            return (Criteria) this;
        }

        public Criteria andKeepNotIn(List<Integer> values) {
            addCriterion("keep not in", values, "keep");
            return (Criteria) this;
        }

        public Criteria andKeepBetween(Integer value1, Integer value2) {
            addCriterion("keep between", value1, value2, "keep");
            return (Criteria) this;
        }

        public Criteria andKeepNotBetween(Integer value1, Integer value2) {
            addCriterion("keep not between", value1, value2, "keep");
            return (Criteria) this;
        }

        public Criteria andAccessIsNull() {
            addCriterion("access is null");
            return (Criteria) this;
        }

        public Criteria andAccessIsNotNull() {
            addCriterion("access is not null");
            return (Criteria) this;
        }

        public Criteria andAccessEqualTo(Integer value) {
            addCriterion("access =", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessNotEqualTo(Integer value) {
            addCriterion("access <>", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessGreaterThan(Integer value) {
            addCriterion("access >", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessGreaterThanOrEqualTo(Integer value) {
            addCriterion("access >=", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessLessThan(Integer value) {
            addCriterion("access <", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessLessThanOrEqualTo(Integer value) {
            addCriterion("access <=", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessIn(List<Integer> values) {
            addCriterion("access in", values, "access");
            return (Criteria) this;
        }

        public Criteria andAccessNotIn(List<Integer> values) {
            addCriterion("access not in", values, "access");
            return (Criteria) this;
        }

        public Criteria andAccessBetween(Integer value1, Integer value2) {
            addCriterion("access between", value1, value2, "access");
            return (Criteria) this;
        }

        public Criteria andAccessNotBetween(Integer value1, Integer value2) {
            addCriterion("access not between", value1, value2, "access");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeIsNull() {
            addCriterion("authoricode is null");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeIsNotNull() {
            addCriterion("authoricode is not null");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeEqualTo(String value) {
            addCriterion("authoricode =", value, "authoricode");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeNotEqualTo(String value) {
            addCriterion("authoricode <>", value, "authoricode");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeGreaterThan(String value) {
            addCriterion("authoricode >", value, "authoricode");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeGreaterThanOrEqualTo(String value) {
            addCriterion("authoricode >=", value, "authoricode");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeLessThan(String value) {
            addCriterion("authoricode <", value, "authoricode");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeLessThanOrEqualTo(String value) {
            addCriterion("authoricode <=", value, "authoricode");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeLike(String value) {
            addCriterion("authoricode like", value, "authoricode");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeNotLike(String value) {
            addCriterion("authoricode not like", value, "authoricode");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeIn(List<String> values) {
            addCriterion("authoricode in", values, "authoricode");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeNotIn(List<String> values) {
            addCriterion("authoricode not in", values, "authoricode");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeBetween(String value1, String value2) {
            addCriterion("authoricode between", value1, value2, "authoricode");
            return (Criteria) this;
        }

        public Criteria andAuthoricodeNotBetween(String value1, String value2) {
            addCriterion("authoricode not between", value1, value2, "authoricode");
            return (Criteria) this;
        }

        public Criteria andUploadbyIsNull() {
            addCriterion("uploadby is null");
            return (Criteria) this;
        }

        public Criteria andUploadbyIsNotNull() {
            addCriterion("uploadby is not null");
            return (Criteria) this;
        }

        public Criteria andUploadbyEqualTo(String value) {
            addCriterion("uploadby =", value, "uploadby");
            return (Criteria) this;
        }

        public Criteria andUploadbyNotEqualTo(String value) {
            addCriterion("uploadby <>", value, "uploadby");
            return (Criteria) this;
        }

        public Criteria andUploadbyGreaterThan(String value) {
            addCriterion("uploadby >", value, "uploadby");
            return (Criteria) this;
        }

        public Criteria andUploadbyGreaterThanOrEqualTo(String value) {
            addCriterion("uploadby >=", value, "uploadby");
            return (Criteria) this;
        }

        public Criteria andUploadbyLessThan(String value) {
            addCriterion("uploadby <", value, "uploadby");
            return (Criteria) this;
        }

        public Criteria andUploadbyLessThanOrEqualTo(String value) {
            addCriterion("uploadby <=", value, "uploadby");
            return (Criteria) this;
        }

        public Criteria andUploadbyLike(String value) {
            addCriterion("uploadby like", value, "uploadby");
            return (Criteria) this;
        }

        public Criteria andUploadbyNotLike(String value) {
            addCriterion("uploadby not like", value, "uploadby");
            return (Criteria) this;
        }

        public Criteria andUploadbyIn(List<String> values) {
            addCriterion("uploadby in", values, "uploadby");
            return (Criteria) this;
        }

        public Criteria andUploadbyNotIn(List<String> values) {
            addCriterion("uploadby not in", values, "uploadby");
            return (Criteria) this;
        }

        public Criteria andUploadbyBetween(String value1, String value2) {
            addCriterion("uploadby between", value1, value2, "uploadby");
            return (Criteria) this;
        }

        public Criteria andUploadbyNotBetween(String value1, String value2) {
            addCriterion("uploadby not between", value1, value2, "uploadby");
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