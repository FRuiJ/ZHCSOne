package com.example.zhcsone.JsonData;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OnePager1 implements Serializable {

    /**
     * total : 4
     * rows : [{"searchValue":null,"createBy":"admin","createTime":"2021-05-06 15:39:53","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":24,"appType":"smart_city","status":"1","sort":1,"advTitle":"开屏广告","advImg":"/prod-api/profile/upload/image/2021/05/06/d2aeef1a-7c47-46bc-8534-20b3d14cd8eb.png","servModule":"","targetId":null,"type":"1"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-06 15:40:17","updateBy":"1","updateTime":"2021-06-25 11:02:40","remark":null,"params":{},"id":25,"appType":"smart_city","status":"1","sort":2,"advTitle":"首页轮播","advImg":"/prod-api/profile/upload/image/2021/05/06/b9d9f081-8a76-41dc-8199-23bcb3a64fcc.png","servModule":"新闻详情","targetId":28,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-06 15:40:59","updateBy":"1111129","updateTime":"2021-06-25 11:02:51","remark":null,"params":{},"id":26,"appType":"smart_city","status":"1","sort":3,"advTitle":"首页轮播","advImg":"/prod-api/profile/upload/image/2021/05/06/e614cb7f-63b0-4cda-bf47-db286ea1b074.png","servModule":"新闻详情","targetId":29,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-06 15:41:20","updateBy":"1111129","updateTime":"2021-06-25 11:03:10","remark":null,"params":{},"id":27,"appType":"smart_city","status":"1","sort":4,"advTitle":"首页轮播","advImg":"/prod-api/profile/upload/image/2021/05/06/242e06f7-9fb0-4e16-b197-206f999c98f2.png","servModule":"新闻详情","targetId":30,"type":"2"}]
     * code : 200
     * msg : 查询成功
     */

    @SerializedName("total")
    private Integer total;
    @SerializedName("code")
    private Integer code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("rows")
    private List<RowsDTO> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public static class RowsDTO implements Serializable {
        /**
         * searchValue : null
         * createBy : admin
         * createTime : 2021-05-06 15:39:53
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 24
         * appType : smart_city
         * status : 1
         * sort : 1
         * advTitle : 开屏广告
         * advImg : /prod-api/profile/upload/image/2021/05/06/d2aeef1a-7c47-46bc-8534-20b3d14cd8eb.png
         * servModule :
         * targetId : null
         * type : 1
         */

        @SerializedName("searchValue")
        private Object searchValue;
        @SerializedName("createBy")
        private String createBy;
        @SerializedName("createTime")
        private String createTime;
        @SerializedName("updateBy")
        private Object updateBy;
        @SerializedName("updateTime")
        private Object updateTime;
        @SerializedName("remark")
        private Object remark;
        @SerializedName("params")
        private ParamsDTO params;
        @SerializedName("id")
        private Integer id;
        @SerializedName("appType")
        private String appType;
        @SerializedName("status")
        private String status;
        @SerializedName("sort")
        private Integer sort;
        @SerializedName("advTitle")
        private String advTitle;
        @SerializedName("advImg")
        private String advImg;
        @SerializedName("servModule")
        private String servModule;
        @SerializedName("targetId")
        private Object targetId;
        @SerializedName("type")
        private String type;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
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

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
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

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public String getAdvTitle() {
            return advTitle;
        }

        public void setAdvTitle(String advTitle) {
            this.advTitle = advTitle;
        }

        public String getAdvImg() {
            return advImg;
        }

        public void setAdvImg(String advImg) {
            this.advImg = advImg;
        }

        public String getServModule() {
            return servModule;
        }

        public void setServModule(String servModule) {
            this.servModule = servModule;
        }

        public Object getTargetId() {
            return targetId;
        }

        public void setTargetId(Object targetId) {
            this.targetId = targetId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class ParamsDTO implements Serializable {
        }
    }
}
