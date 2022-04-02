package com.ming.workspace.data;

public class CodeDetailBean {

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
         * id
         */
        private String id;
        /**
         * tenantId
         */
        private String tenantId;
        /**
         * batchId
         */
        private String batchId;
        /**
         * scene
         */
        private String scene;
        /**
         * state
         */
        private Integer state;
        /**
         * scanNumber
         */
        private String scanNumber;
        /**
         * userNumber
         */
        private String userNumber;
        /**
         * filePath
         */
        private String filePath;
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

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getBatchId() {
            return batchId;
        }

        public void setBatchId(String batchId) {
            this.batchId = batchId;
        }

        public String getScene() {
            return scene;
        }

        public void setScene(String scene) {
            this.scene = scene;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public String getScanNumber() {
            return scanNumber;
        }

        public void setScanNumber(String scanNumber) {
            this.scanNumber = scanNumber;
        }

        public String getUserNumber() {
            return userNumber;
        }

        public void setUserNumber(String userNumber) {
            this.userNumber = userNumber;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
