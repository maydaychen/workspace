package com.huanxin.workspace.data;

import java.util.List;

public class WorkspaceListBean {
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
         * total
         */
        private Integer total;
        /**
         * items
         */
        private List<ItemsBean> items;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
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
             * deviceName
             */
            private String deviceName;
            /**
             * deviceImage
             */
            private String deviceImage;
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
            private String source;

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

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public String getDeviceImage() {
                return deviceImage;
            }

            public void setDeviceImage(String deviceImage) {
                this.deviceImage = deviceImage;
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

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }
        }
    }
}
