package com.huanxin.workspace.data;

public class DeviceDetailBean {
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
         * sn
         */
        private String sn;
        /**
         * productId
         */
        private String productId;
        private String deviceName;
        /**
         * latitude
         */
        private String latitude;
        /**
         * longitude
         */
        private String longitude;
        /**
         * deliveryAddress
         */
        private String deliveryAddress;
        /**
         * address
         */
        private String address;
        /**
         * deviceIot
         */
        private String deviceIot;
        /**
         * deviceBattery
         */
        private String deviceBattery;
        /**
         * batteryImage
         */
        private String batteryImage;
        /**
         * platformId
         */
        private String platformId;
        /**
         * customerId
         */
        private String customerId;
        private String customerName;

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

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDeviceIot() {
            return deviceIot;
        }

        public void setDeviceIot(String deviceIot) {
            this.deviceIot = deviceIot;
        }

        public String getDeviceBattery() {
            return deviceBattery;
        }

        public void setDeviceBattery(String deviceBattery) {
            this.deviceBattery = deviceBattery;
        }

        public String getBatteryImage() {
            return batteryImage;
        }

        public void setBatteryImage(String batteryImage) {
            this.batteryImage = batteryImage;
        }

        public String getPlatformId() {
            return platformId;
        }

        public void setPlatformId(String platformId) {
            this.platformId = platformId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName == null ? "" : customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }
    }
}
