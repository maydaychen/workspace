package com.ming.workspace.data;


public class UserBean {

    /**
     * code
     */
    private Integer code;
    /**
     * msg
     */
    private String msg;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    /**
     * data
     */
    private DataBean data;

    /**
     * DataBean
     */
    public static class DataBean {
        /**
         * access_token
         */
        private String access_token;
        /**
         * expires_in
         */
        private Integer expires_in;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public Integer getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(Integer expires_in) {
            this.expires_in = expires_in;
        }
    }
}
