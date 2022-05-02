package com.huanxin.workspace.data;

public class WorkspaceDetailBean {
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
         * deviceId
         */
        private String deviceId;
        /**
         * deviceSn
         */
        private String deviceSn;
        /**
         * ticketType
         */
        private String ticketType;
        /**
         * ticketContent
         */
        private String ticketContent;
        /**
         * ticketState
         */
        private Integer ticketState;
        /**
         * ticketRecommend
         */
        private String ticketRecommend;
        /**
         * ticketResultState
         */
        private Integer ticketResultState;
        /**
         * reporterId
         */
        private String reporterId;
        /**
         * reporterPhone
         */
        private String reporterPhone;
        /**
         * source
         */
        private Integer source;
        /**
         * createBy
         */
        private String createBy;
        /**
         * createTime
         */
        private Long createTime;
        /**
         * delFlag
         */
        private Boolean delFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceSn() {
            return deviceSn;
        }

        public void setDeviceSn(String deviceSn) {
            this.deviceSn = deviceSn;
        }

        public String getTicketType() {
            return ticketType;
        }

        public void setTicketType(String ticketType) {
            this.ticketType = ticketType;
        }

        public String getTicketContent() {
            return ticketContent;
        }

        public void setTicketContent(String ticketContent) {
            this.ticketContent = ticketContent;
        }

        public Integer getTicketState() {
            return ticketState;
        }

        public void setTicketState(Integer ticketState) {
            this.ticketState = ticketState;
        }

        public String getTicketRecommend() {
            return ticketRecommend;
        }

        public void setTicketRecommend(String ticketRecommend) {
            this.ticketRecommend = ticketRecommend;
        }

        public Integer getTicketResultState() {
            return ticketResultState;
        }

        public void setTicketResultState(Integer ticketResultState) {
            this.ticketResultState = ticketResultState;
        }

        public String getReporterId() {
            return reporterId;
        }

        public void setReporterId(String reporterId) {
            this.reporterId = reporterId;
        }

        public String getReporterPhone() {
            return reporterPhone;
        }

        public void setReporterPhone(String reporterPhone) {
            this.reporterPhone = reporterPhone;
        }

        public Integer getSource() {
            return source;
        }

        public void setSource(Integer source) {
            this.source = source;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public Boolean getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Boolean delFlag) {
            this.delFlag = delFlag;
        }
    }
}
