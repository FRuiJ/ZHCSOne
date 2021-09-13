package com.example.zhcsone.JsonData;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SubContextData implements Serializable {
    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private DataDTO data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("first")
        private String first;
        @SerializedName("end")
        private String end;
        @SerializedName("startTime")
        private String startTime;
        @SerializedName("endTime")
        private String endTime;
        @SerializedName("cityId")
        private int cityId;
        @SerializedName("stationsNumber")
        private int stationsNumber;
        @SerializedName("km")
        private int km;
        @SerializedName("runStationsName")
        private String runStationsName;
        @SerializedName("remainingTime")
        private int remainingTime;
        @SerializedName("metroStepList")
        private List<MetroStepListDTO> metroStepList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getStationsNumber() {
            return stationsNumber;
        }

        public void setStationsNumber(int stationsNumber) {
            this.stationsNumber = stationsNumber;
        }

        public int getKm() {
            return km;
        }

        public void setKm(int km) {
            this.km = km;
        }

        public String getRunStationsName() {
            return runStationsName;
        }

        public void setRunStationsName(String runStationsName) {
            this.runStationsName = runStationsName;
        }

        public int getRemainingTime() {
            return remainingTime;
        }

        public void setRemainingTime(int remainingTime) {
            this.remainingTime = remainingTime;
        }

        public List<MetroStepListDTO> getMetroStepList() {
            return metroStepList;
        }

        public void setMetroStepList(List<MetroStepListDTO> metroStepList) {
            this.metroStepList = metroStepList;
        }

        public static class MetroStepListDTO implements Serializable {
            @SerializedName("searchValue")
            private Object searchValue;
            @SerializedName("createBy")
            private Object createBy;
            @SerializedName("createTime")
            private String createTime;
            @SerializedName("updateBy")
            private Object updateBy;
            @SerializedName("updateTime")
            private String updateTime;
            @SerializedName("remark")
            private Object remark;
            @SerializedName("params")
            private ParamsDTO params;
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;
            @SerializedName("seq")
            private int seq;
            @SerializedName("lineId")
            private int lineId;
            @SerializedName("firstCh")
            private String firstCh;

            public Object getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(Object searchValue) {
                this.searchValue = searchValue;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public ParamsDTO getParams() {
                return params;
            }

            public void setParams(ParamsDTO params) {
                this.params = params;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public int getLineId() {
                return lineId;
            }

            public void setLineId(int lineId) {
                this.lineId = lineId;
            }

            public String getFirstCh() {
                return firstCh;
            }

            public void setFirstCh(String firstCh) {
                this.firstCh = firstCh;
            }

            public static class ParamsDTO implements Serializable {
            }
        }
    }
}
