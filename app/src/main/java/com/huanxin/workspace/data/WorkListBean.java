package com.huanxin.workspace.data;

import java.util.List;

public class WorkListBean {

    /**
     * msg
     */
    private String msg;
    /**
     * code
     */
    private Integer code;
    /**
     * data
     */
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * records
         */
        private List<RecordsBean> records;
        /**
         * total
         */
        private Integer total;
        /**
         * size
         */
        private Integer size;
        /**
         * current
         */
        private Integer current;
        /**
         * orders
         */
        private List<?> orders;
        /**
         * optimizeCountSql
         */
        private Boolean optimizeCountSql;
        /**
         * searchCount
         */
        private Boolean searchCount;
        /**
         * pages
         */
        private Integer pages;

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Integer getCurrent() {
            return current;
        }

        public void setCurrent(Integer current) {
            this.current = current;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public Boolean getOptimizeCountSql() {
            return optimizeCountSql;
        }

        public void setOptimizeCountSql(Boolean optimizeCountSql) {
            this.optimizeCountSql = optimizeCountSql;
        }

        public Boolean getSearchCount() {
            return searchCount;
        }

        public void setSearchCount(Boolean searchCount) {
            this.searchCount = searchCount;
        }

        public Integer getPages() {
            return pages;
        }

        public void setPages(Integer pages) {
            this.pages = pages;
        }

        public static class RecordsBean {
            /**
             * id
             */
            private String id;
            /**
             * sn
             */
            private String sn;
            /**
             * deviceId
             */
            private String deviceId;
            /**
             * deviceName
             */
            private String deviceName;
            /**
             * userId
             */
            private String userId;
            /**
             * userPhone
             */
            private String userPhone;
            /**
             * state
             */
            private Integer state;
            /**
             * createTime
             */
            private String createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public Integer getState() {
                return state;
            }

            public void setState(Integer state) {
                this.state = state;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
