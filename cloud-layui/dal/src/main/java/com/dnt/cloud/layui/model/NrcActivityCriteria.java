package com.dnt.cloud.layui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NrcActivityCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NrcActivityCriteria() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameChinaIsNull() {
            addCriterion("NAME_CHINA is null");
            return (Criteria) this;
        }

        public Criteria andNameChinaIsNotNull() {
            addCriterion("NAME_CHINA is not null");
            return (Criteria) this;
        }

        public Criteria andNameChinaEqualTo(String value) {
            addCriterion("NAME_CHINA =", value, "nameChina");
            return (Criteria) this;
        }

        public Criteria andNameChinaNotEqualTo(String value) {
            addCriterion("NAME_CHINA <>", value, "nameChina");
            return (Criteria) this;
        }

        public Criteria andNameChinaGreaterThan(String value) {
            addCriterion("NAME_CHINA >", value, "nameChina");
            return (Criteria) this;
        }

        public Criteria andNameChinaGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_CHINA >=", value, "nameChina");
            return (Criteria) this;
        }

        public Criteria andNameChinaLessThan(String value) {
            addCriterion("NAME_CHINA <", value, "nameChina");
            return (Criteria) this;
        }

        public Criteria andNameChinaLessThanOrEqualTo(String value) {
            addCriterion("NAME_CHINA <=", value, "nameChina");
            return (Criteria) this;
        }

        public Criteria andNameChinaLike(String value) {
            addCriterion("NAME_CHINA like", value, "nameChina");
            return (Criteria) this;
        }

        public Criteria andNameChinaNotLike(String value) {
            addCriterion("NAME_CHINA not like", value, "nameChina");
            return (Criteria) this;
        }

        public Criteria andNameChinaIn(List<String> values) {
            addCriterion("NAME_CHINA in", values, "nameChina");
            return (Criteria) this;
        }

        public Criteria andNameChinaNotIn(List<String> values) {
            addCriterion("NAME_CHINA not in", values, "nameChina");
            return (Criteria) this;
        }

        public Criteria andNameChinaBetween(String value1, String value2) {
            addCriterion("NAME_CHINA between", value1, value2, "nameChina");
            return (Criteria) this;
        }

        public Criteria andNameChinaNotBetween(String value1, String value2) {
            addCriterion("NAME_CHINA not between", value1, value2, "nameChina");
            return (Criteria) this;
        }

        public Criteria andNameEngIsNull() {
            addCriterion("NAME_ENG is null");
            return (Criteria) this;
        }

        public Criteria andNameEngIsNotNull() {
            addCriterion("NAME_ENG is not null");
            return (Criteria) this;
        }

        public Criteria andNameEngEqualTo(String value) {
            addCriterion("NAME_ENG =", value, "nameEng");
            return (Criteria) this;
        }

        public Criteria andNameEngNotEqualTo(String value) {
            addCriterion("NAME_ENG <>", value, "nameEng");
            return (Criteria) this;
        }

        public Criteria andNameEngGreaterThan(String value) {
            addCriterion("NAME_ENG >", value, "nameEng");
            return (Criteria) this;
        }

        public Criteria andNameEngGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_ENG >=", value, "nameEng");
            return (Criteria) this;
        }

        public Criteria andNameEngLessThan(String value) {
            addCriterion("NAME_ENG <", value, "nameEng");
            return (Criteria) this;
        }

        public Criteria andNameEngLessThanOrEqualTo(String value) {
            addCriterion("NAME_ENG <=", value, "nameEng");
            return (Criteria) this;
        }

        public Criteria andNameEngLike(String value) {
            addCriterion("NAME_ENG like", value, "nameEng");
            return (Criteria) this;
        }

        public Criteria andNameEngNotLike(String value) {
            addCriterion("NAME_ENG not like", value, "nameEng");
            return (Criteria) this;
        }

        public Criteria andNameEngIn(List<String> values) {
            addCriterion("NAME_ENG in", values, "nameEng");
            return (Criteria) this;
        }

        public Criteria andNameEngNotIn(List<String> values) {
            addCriterion("NAME_ENG not in", values, "nameEng");
            return (Criteria) this;
        }

        public Criteria andNameEngBetween(String value1, String value2) {
            addCriterion("NAME_ENG between", value1, value2, "nameEng");
            return (Criteria) this;
        }

        public Criteria andNameEngNotBetween(String value1, String value2) {
            addCriterion("NAME_ENG not between", value1, value2, "nameEng");
            return (Criteria) this;
        }

        public Criteria andNameFanIsNull() {
            addCriterion("NAME_FAN is null");
            return (Criteria) this;
        }

        public Criteria andNameFanIsNotNull() {
            addCriterion("NAME_FAN is not null");
            return (Criteria) this;
        }

        public Criteria andNameFanEqualTo(String value) {
            addCriterion("NAME_FAN =", value, "nameFan");
            return (Criteria) this;
        }

        public Criteria andNameFanNotEqualTo(String value) {
            addCriterion("NAME_FAN <>", value, "nameFan");
            return (Criteria) this;
        }

        public Criteria andNameFanGreaterThan(String value) {
            addCriterion("NAME_FAN >", value, "nameFan");
            return (Criteria) this;
        }

        public Criteria andNameFanGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_FAN >=", value, "nameFan");
            return (Criteria) this;
        }

        public Criteria andNameFanLessThan(String value) {
            addCriterion("NAME_FAN <", value, "nameFan");
            return (Criteria) this;
        }

        public Criteria andNameFanLessThanOrEqualTo(String value) {
            addCriterion("NAME_FAN <=", value, "nameFan");
            return (Criteria) this;
        }

        public Criteria andNameFanLike(String value) {
            addCriterion("NAME_FAN like", value, "nameFan");
            return (Criteria) this;
        }

        public Criteria andNameFanNotLike(String value) {
            addCriterion("NAME_FAN not like", value, "nameFan");
            return (Criteria) this;
        }

        public Criteria andNameFanIn(List<String> values) {
            addCriterion("NAME_FAN in", values, "nameFan");
            return (Criteria) this;
        }

        public Criteria andNameFanNotIn(List<String> values) {
            addCriterion("NAME_FAN not in", values, "nameFan");
            return (Criteria) this;
        }

        public Criteria andNameFanBetween(String value1, String value2) {
            addCriterion("NAME_FAN between", value1, value2, "nameFan");
            return (Criteria) this;
        }

        public Criteria andNameFanNotBetween(String value1, String value2) {
            addCriterion("NAME_FAN not between", value1, value2, "nameFan");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNull() {
            addCriterion("IMAGE_URL is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("IMAGE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("IMAGE_URL =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("IMAGE_URL <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("IMAGE_URL >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("IMAGE_URL >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("IMAGE_URL <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("IMAGE_URL <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("IMAGE_URL like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("IMAGE_URL not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("IMAGE_URL in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("IMAGE_URL not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("IMAGE_URL between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("IMAGE_URL not between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaIsNull() {
            addCriterion("DETAILS_CHINA is null");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaIsNotNull() {
            addCriterion("DETAILS_CHINA is not null");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaEqualTo(String value) {
            addCriterion("DETAILS_CHINA =", value, "detailsChina");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaNotEqualTo(String value) {
            addCriterion("DETAILS_CHINA <>", value, "detailsChina");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaGreaterThan(String value) {
            addCriterion("DETAILS_CHINA >", value, "detailsChina");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaGreaterThanOrEqualTo(String value) {
            addCriterion("DETAILS_CHINA >=", value, "detailsChina");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaLessThan(String value) {
            addCriterion("DETAILS_CHINA <", value, "detailsChina");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaLessThanOrEqualTo(String value) {
            addCriterion("DETAILS_CHINA <=", value, "detailsChina");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaLike(String value) {
            addCriterion("DETAILS_CHINA like", value, "detailsChina");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaNotLike(String value) {
            addCriterion("DETAILS_CHINA not like", value, "detailsChina");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaIn(List<String> values) {
            addCriterion("DETAILS_CHINA in", values, "detailsChina");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaNotIn(List<String> values) {
            addCriterion("DETAILS_CHINA not in", values, "detailsChina");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaBetween(String value1, String value2) {
            addCriterion("DETAILS_CHINA between", value1, value2, "detailsChina");
            return (Criteria) this;
        }

        public Criteria andDetailsChinaNotBetween(String value1, String value2) {
            addCriterion("DETAILS_CHINA not between", value1, value2, "detailsChina");
            return (Criteria) this;
        }

        public Criteria andDetailsEngIsNull() {
            addCriterion("DETAILS_ENG is null");
            return (Criteria) this;
        }

        public Criteria andDetailsEngIsNotNull() {
            addCriterion("DETAILS_ENG is not null");
            return (Criteria) this;
        }

        public Criteria andDetailsEngEqualTo(String value) {
            addCriterion("DETAILS_ENG =", value, "detailsEng");
            return (Criteria) this;
        }

        public Criteria andDetailsEngNotEqualTo(String value) {
            addCriterion("DETAILS_ENG <>", value, "detailsEng");
            return (Criteria) this;
        }

        public Criteria andDetailsEngGreaterThan(String value) {
            addCriterion("DETAILS_ENG >", value, "detailsEng");
            return (Criteria) this;
        }

        public Criteria andDetailsEngGreaterThanOrEqualTo(String value) {
            addCriterion("DETAILS_ENG >=", value, "detailsEng");
            return (Criteria) this;
        }

        public Criteria andDetailsEngLessThan(String value) {
            addCriterion("DETAILS_ENG <", value, "detailsEng");
            return (Criteria) this;
        }

        public Criteria andDetailsEngLessThanOrEqualTo(String value) {
            addCriterion("DETAILS_ENG <=", value, "detailsEng");
            return (Criteria) this;
        }

        public Criteria andDetailsEngLike(String value) {
            addCriterion("DETAILS_ENG like", value, "detailsEng");
            return (Criteria) this;
        }

        public Criteria andDetailsEngNotLike(String value) {
            addCriterion("DETAILS_ENG not like", value, "detailsEng");
            return (Criteria) this;
        }

        public Criteria andDetailsEngIn(List<String> values) {
            addCriterion("DETAILS_ENG in", values, "detailsEng");
            return (Criteria) this;
        }

        public Criteria andDetailsEngNotIn(List<String> values) {
            addCriterion("DETAILS_ENG not in", values, "detailsEng");
            return (Criteria) this;
        }

        public Criteria andDetailsEngBetween(String value1, String value2) {
            addCriterion("DETAILS_ENG between", value1, value2, "detailsEng");
            return (Criteria) this;
        }

        public Criteria andDetailsEngNotBetween(String value1, String value2) {
            addCriterion("DETAILS_ENG not between", value1, value2, "detailsEng");
            return (Criteria) this;
        }

        public Criteria andDetailsFanIsNull() {
            addCriterion("DETAILS_FAN is null");
            return (Criteria) this;
        }

        public Criteria andDetailsFanIsNotNull() {
            addCriterion("DETAILS_FAN is not null");
            return (Criteria) this;
        }

        public Criteria andDetailsFanEqualTo(String value) {
            addCriterion("DETAILS_FAN =", value, "detailsFan");
            return (Criteria) this;
        }

        public Criteria andDetailsFanNotEqualTo(String value) {
            addCriterion("DETAILS_FAN <>", value, "detailsFan");
            return (Criteria) this;
        }

        public Criteria andDetailsFanGreaterThan(String value) {
            addCriterion("DETAILS_FAN >", value, "detailsFan");
            return (Criteria) this;
        }

        public Criteria andDetailsFanGreaterThanOrEqualTo(String value) {
            addCriterion("DETAILS_FAN >=", value, "detailsFan");
            return (Criteria) this;
        }

        public Criteria andDetailsFanLessThan(String value) {
            addCriterion("DETAILS_FAN <", value, "detailsFan");
            return (Criteria) this;
        }

        public Criteria andDetailsFanLessThanOrEqualTo(String value) {
            addCriterion("DETAILS_FAN <=", value, "detailsFan");
            return (Criteria) this;
        }

        public Criteria andDetailsFanLike(String value) {
            addCriterion("DETAILS_FAN like", value, "detailsFan");
            return (Criteria) this;
        }

        public Criteria andDetailsFanNotLike(String value) {
            addCriterion("DETAILS_FAN not like", value, "detailsFan");
            return (Criteria) this;
        }

        public Criteria andDetailsFanIn(List<String> values) {
            addCriterion("DETAILS_FAN in", values, "detailsFan");
            return (Criteria) this;
        }

        public Criteria andDetailsFanNotIn(List<String> values) {
            addCriterion("DETAILS_FAN not in", values, "detailsFan");
            return (Criteria) this;
        }

        public Criteria andDetailsFanBetween(String value1, String value2) {
            addCriterion("DETAILS_FAN between", value1, value2, "detailsFan");
            return (Criteria) this;
        }

        public Criteria andDetailsFanNotBetween(String value1, String value2) {
            addCriterion("DETAILS_FAN not between", value1, value2, "detailsFan");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterionForJDBCDate("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterionForJDBCDate("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterionForJDBCDate("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterionForJDBCDate("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andMarketIdIsNull() {
            addCriterion("MARKET_ID is null");
            return (Criteria) this;
        }

        public Criteria andMarketIdIsNotNull() {
            addCriterion("MARKET_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMarketIdEqualTo(Long value) {
            addCriterion("MARKET_ID =", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotEqualTo(Long value) {
            addCriterion("MARKET_ID <>", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdGreaterThan(Long value) {
            addCriterion("MARKET_ID >", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MARKET_ID >=", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdLessThan(Long value) {
            addCriterion("MARKET_ID <", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdLessThanOrEqualTo(Long value) {
            addCriterion("MARKET_ID <=", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdIn(List<Long> values) {
            addCriterion("MARKET_ID in", values, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotIn(List<Long> values) {
            addCriterion("MARKET_ID not in", values, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdBetween(Long value1, Long value2) {
            addCriterion("MARKET_ID between", value1, value2, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotBetween(Long value1, Long value2) {
            addCriterion("MARKET_ID not between", value1, value2, "marketId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("OPERATOR is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("OPERATOR is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("OPERATOR =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("OPERATOR <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("OPERATOR >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATOR >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("OPERATOR <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("OPERATOR <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("OPERATOR like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("OPERATOR not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("OPERATOR in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("OPERATOR not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("OPERATOR between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("OPERATOR not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("GMT_CREATE is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("GMT_CREATE is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("GMT_CREATE =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("GMT_CREATE <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("GMT_CREATE >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("GMT_CREATE >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("GMT_CREATE <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("GMT_CREATE <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("GMT_CREATE in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("GMT_CREATE not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("GMT_CREATE between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("GMT_CREATE not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNull() {
            addCriterion("GMT_MODIFY is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNotNull() {
            addCriterion("GMT_MODIFY is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyEqualTo(Date value) {
            addCriterion("GMT_MODIFY =", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotEqualTo(Date value) {
            addCriterion("GMT_MODIFY <>", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThan(Date value) {
            addCriterion("GMT_MODIFY >", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThanOrEqualTo(Date value) {
            addCriterion("GMT_MODIFY >=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThan(Date value) {
            addCriterion("GMT_MODIFY <", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThanOrEqualTo(Date value) {
            addCriterion("GMT_MODIFY <=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIn(List<Date> values) {
            addCriterion("GMT_MODIFY in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotIn(List<Date> values) {
            addCriterion("GMT_MODIFY not in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyBetween(Date value1, Date value2) {
            addCriterion("GMT_MODIFY between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotBetween(Date value1, Date value2) {
            addCriterion("GMT_MODIFY not between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andExtensionIsNull() {
            addCriterion("EXTENSION is null");
            return (Criteria) this;
        }

        public Criteria andExtensionIsNotNull() {
            addCriterion("EXTENSION is not null");
            return (Criteria) this;
        }

        public Criteria andExtensionEqualTo(String value) {
            addCriterion("EXTENSION =", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionNotEqualTo(String value) {
            addCriterion("EXTENSION <>", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionGreaterThan(String value) {
            addCriterion("EXTENSION >", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionGreaterThanOrEqualTo(String value) {
            addCriterion("EXTENSION >=", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionLessThan(String value) {
            addCriterion("EXTENSION <", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionLessThanOrEqualTo(String value) {
            addCriterion("EXTENSION <=", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionLike(String value) {
            addCriterion("EXTENSION like", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionNotLike(String value) {
            addCriterion("EXTENSION not like", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionIn(List<String> values) {
            addCriterion("EXTENSION in", values, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionNotIn(List<String> values) {
            addCriterion("EXTENSION not in", values, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionBetween(String value1, String value2) {
            addCriterion("EXTENSION between", value1, value2, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionNotBetween(String value1, String value2) {
            addCriterion("EXTENSION not between", value1, value2, "extension");
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