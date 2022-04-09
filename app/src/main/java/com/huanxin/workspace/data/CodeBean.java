package com.huanxin.workspace.data;

public class CodeBean {

    private String msg;
    private Integer code;

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

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    private DataDTO data;

    public static class DataDTO {
        private String img;
        private String uuid;
        private Boolean captchaOnOff;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public Boolean getCaptchaOnOff() {
            return captchaOnOff;
        }

        public void setCaptchaOnOff(Boolean captchaOnOff) {
            this.captchaOnOff = captchaOnOff;
        }
    }
}
