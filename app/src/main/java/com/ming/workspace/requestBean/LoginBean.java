package com.ming.workspace.requestBean;

public class LoginBean {
    public LoginBean(String enterpriseName, String password, String userName, String uuid, String code) {
        this.enterpriseName = enterpriseName;
        this.password = password;
        this.userName = userName;
        this.uuid = uuid;
        this.code = code;
    }

    private String enterpriseName;
    private String password;
    private String userName;
    private String uuid;
    private String code;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
