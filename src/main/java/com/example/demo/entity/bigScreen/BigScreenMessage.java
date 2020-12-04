package com.example.demo.entity.bigScreen;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 大屏信息
 */
public class BigScreenMessage {

    JSONArray riskPointCount;
    JSONArray riskPointList;
    JSONArray todayCheck;
    JSONArray weeklyCheck;
    JSONArray warningRiskPoint;
    JSONArray hiddenCount;
    JSONArray hiddenBulletin;
    JSONArray hiddenBulletinGent;
    JSONArray riskPointControlRecord;
    JSONObject allIndustryStatistics;
    JSONArray securityScore;
    JSONArray riskIndex;
    JSONArray riskPointMapList;

    JSONArray riskPointControlRecordGent;
    JSONArray hiddenCountGent;
    JSONArray riskPointCountGent;


    BigScreenMessage(final JSONArray riskPointCount, final JSONArray riskPointList, final JSONArray todayCheck, final JSONArray weeklyCheck,
                     final JSONArray warningRiskPoint, final JSONArray hiddenCount, final JSONArray hiddenBulletin, final JSONArray hiddenBulletinGent,
                     final JSONArray riskPointControlRecord, final JSONObject allIndustryStatistics, final JSONArray securityScore, final JSONArray riskIndex,
                     final JSONArray riskPointMapList, final JSONArray riskPointCountGent , final JSONArray hiddenCountGent, final JSONArray riskPointControlRecordGent) {
        this.riskPointCount = riskPointCount;
        this.riskPointList = riskPointList;
        this.todayCheck = todayCheck;
        this.weeklyCheck = weeklyCheck;
        this.warningRiskPoint = warningRiskPoint;
        this.hiddenCount = hiddenCount;
        this.hiddenBulletin = hiddenBulletin;
        this.hiddenBulletinGent = hiddenBulletinGent;
        this.riskPointControlRecord = riskPointControlRecord;
        this.allIndustryStatistics = allIndustryStatistics;
        this.securityScore = securityScore;
        this.riskIndex = riskIndex;
        this.riskPointMapList = riskPointMapList;
        this.riskPointControlRecordGent = riskPointControlRecordGent;
        this.hiddenCountGent = hiddenCountGent;
        this.riskPointCountGent = riskPointCountGent;
    }

    public static BigScreenMessage.BigScreenMessageBuilder builder() {
        return new BigScreenMessage.BigScreenMessageBuilder();
    }

    public JSONArray getRiskPointCount() {
        return this.riskPointCount;
    }

    public JSONArray getRiskPointList() {
        return this.riskPointList;
    }

    public JSONArray getTodayCheck() {
        return this.todayCheck;
    }

    public JSONArray getWeeklyCheck() {
        return this.weeklyCheck;
    }

    public JSONArray getWarningRiskPoint() {
        return this.warningRiskPoint;
    }

    public JSONArray getRiskPointControlRecord() {
        return this.riskPointControlRecord;
    }

    public JSONObject getAllIndustryStatistics() {
        return this.allIndustryStatistics;
    }

    public void setRiskPointCount(final JSONArray riskPointCount) {
        this.riskPointCount = riskPointCount;
    }

    public void setRiskPointList(final JSONArray riskPointList) {
        this.riskPointList = riskPointList;
    }

    public void setTodayCheck(final JSONArray todayCheck) {
        this.todayCheck = todayCheck;
    }

    public void setWeeklyCheck(final JSONArray weeklyCheck) {
        this.weeklyCheck = weeklyCheck;
    }

    public void setWarningRiskPoint(final JSONArray warningRiskPoint) {
        this.warningRiskPoint = warningRiskPoint;
    }

    public JSONArray getHiddenCount() {
        return hiddenCount;
    }

    public void setHiddenCount(JSONArray hiddenCount) {
        this.hiddenCount = hiddenCount;
    }

    public JSONArray getHiddenBulletin() {
        return hiddenBulletin;
    }

    public void setHiddenBulletin(JSONArray hiddenBulletin) {
        this.hiddenBulletin = hiddenBulletin;
    }

    public void setRiskPointControlRecord(final JSONArray riskPointControlRecord) {
        this.riskPointControlRecord = riskPointControlRecord;
    }

    public void setAllIndustryStatistics(final JSONObject allIndustryStatistics) {
        this.allIndustryStatistics = allIndustryStatistics;
    }

    public JSONArray getSecurityScore() {
        return securityScore;
    }

    public void setSecurityScore(JSONArray securityScore) {
        this.securityScore = securityScore;
    }

    public JSONArray getRiskIndex() {
        return riskIndex;
    }

    public void setRiskIndex(JSONArray riskIndex) {
        this.riskIndex = riskIndex;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BigScreenMessage;
    }

    public String toString() {
        return "BigScreenMessage(riskPointCount=" + this.getRiskPointCount() + ", riskPointList=" + this.getRiskPointList() + ", todayCheck=" + this.getTodayCheck() + ", weeklyCheck=" + this.getWeeklyCheck() + ", warningRiskPoint=" + this.getWarningRiskPoint() + ", hiddenDangerCount=" + this.getHiddenCount() + ", hiddenDangerBulletin=" + this.getHiddenBulletin() + ", riskPointControlRecord=" + this.getRiskPointControlRecord() + ", allIndustryStatistics=" + this.getAllIndustryStatistics() + ",riskPointMapList="+this.riskPointMapList+")";
    }

    public JSONArray getRiskPointMapList() {
        return riskPointMapList;
    }

    public void setRiskPointMapList(JSONArray riskPointMapList) {
        this.riskPointMapList = riskPointMapList;
    }

    public static class BigScreenMessageBuilder {
        private JSONArray riskPointCount;
        private JSONArray riskPointList;
        private JSONArray todayCheck;
        private JSONArray weeklyCheck;
        private JSONArray warningRiskPoint;
        private JSONArray hiddenCount;
        private JSONArray hiddenBulletin;
        private JSONArray hiddenBulletinGent;
        private JSONArray riskPointControlRecord;
        private JSONObject allIndustryStatistics;
        private JSONArray securityScore;
        private JSONArray riskIndex;
        private JSONArray riskPointMapList;

        private JSONArray riskPointControlRecordGent;
        private JSONArray hiddenCountGent;
        private JSONArray riskPointCountGent;



        BigScreenMessageBuilder() {
        }


        public BigScreenMessage.BigScreenMessageBuilder riskPointCount(final JSONArray riskPointCount) {
            this.riskPointCount = riskPointCount;
            return this;
        }

        public BigScreenMessage.BigScreenMessageBuilder riskPointList(final JSONArray riskPointList) {
            this.riskPointList = riskPointList;
            return this;
        }

        public BigScreenMessage.BigScreenMessageBuilder todayCheck(final JSONArray todayCheck) {
            this.todayCheck = todayCheck;
            return this;
        }

        public BigScreenMessage.BigScreenMessageBuilder weeklyCheck(final JSONArray weeklyCheck) {
            this.weeklyCheck = weeklyCheck;
            return this;
        }

        public BigScreenMessage.BigScreenMessageBuilder warningRiskPoint(final JSONArray warningRiskPoint) {
            this.warningRiskPoint = warningRiskPoint;
            return this;
        }

        public BigScreenMessage.BigScreenMessageBuilder hiddenCount(final JSONArray hiddenCount) {
            this.hiddenCount = hiddenCount;
            return this;
        }

        public BigScreenMessage.BigScreenMessageBuilder hiddenBulletin(final JSONArray hiddenBulletin) {
            this.hiddenBulletin = hiddenBulletin;
            return this;
        }

        public BigScreenMessage.BigScreenMessageBuilder hiddenBulletinGent(final JSONArray hiddenBulletinGent) {
            this.hiddenBulletinGent = hiddenBulletinGent;
            return this;
        }

        public BigScreenMessage.BigScreenMessageBuilder riskPointControlRecord(final JSONArray riskPointControlRecord) {
            this.riskPointControlRecord = riskPointControlRecord;
            return this;
        }

        public BigScreenMessage.BigScreenMessageBuilder allIndustryStatistics(final JSONObject allIndustryStatistics) {
            this.allIndustryStatistics = allIndustryStatistics;
            return this;
        }

        public BigScreenMessage.BigScreenMessageBuilder securityScore(final JSONArray securityScore) {
            this.securityScore = securityScore;
            return this;
        }
        public BigScreenMessage.BigScreenMessageBuilder riskIndex(final JSONArray riskIndex) {
            this.riskIndex = riskIndex;
            return this;
        }

        public BigScreenMessage.BigScreenMessageBuilder riskPointMapList(final JSONArray riskPointMapList) {
            this.riskPointMapList = riskPointMapList;
            return this;
        }

        public BigScreenMessage.BigScreenMessageBuilder riskPointCountGent(final JSONArray riskPointCountGent) {
            this.riskPointCountGent = riskPointCountGent;
            return this;
        }
        public BigScreenMessage.BigScreenMessageBuilder hiddenCountGent(final JSONArray hiddenCountGent) {
            this.hiddenCountGent = hiddenCountGent;
            return this;
        }
        public BigScreenMessage.BigScreenMessageBuilder riskPointControlRecordGent(final JSONArray riskPointControlRecordGent) {
            this.riskPointControlRecordGent = riskPointControlRecordGent;
            return this;
        }


        public BigScreenMessage build() {
            return new BigScreenMessage(this.riskPointCount, this.riskPointList, this.todayCheck, this.weeklyCheck, this.warningRiskPoint, this.hiddenCount,
                    this.hiddenBulletin, this.hiddenBulletinGent,this.riskPointControlRecord, this.allIndustryStatistics,this.securityScore,this.riskIndex,
                    this.riskPointMapList,this.riskPointCountGent,this.hiddenCountGent,this.riskPointControlRecordGent);
        }

        @Override
        public String toString() {
            return "BigScreenMessageBuilder{" +
                    "riskPointCount=" + riskPointCount +
                    ", riskPointList=" + riskPointList +
                    ", todayCheck=" + todayCheck +
                    ", weeklyCheck=" + weeklyCheck +
                    ", warningRiskPoint=" + warningRiskPoint +
                    ", hiddenCount=" + hiddenCount +
                    ", hiddenBulletin=" + hiddenBulletin +
                    ", hiddenBulletinGent=" + hiddenBulletinGent +
                    ", riskPointControlRecord=" + riskPointControlRecord +
                    ", allIndustryStatistics=" + allIndustryStatistics +
                    ", securityScore=" + securityScore +
                    ", riskIndex=" + riskIndex +
                    ", riskPointMapList=" + riskPointMapList +
                    ", riskPointCountGent=" + riskPointCountGent +
                    ", hiddenCountGent=" + hiddenCountGent +
                    ", riskPointControlRecordGent=" + riskPointControlRecordGent +
                    '}';
        }
    }
}
