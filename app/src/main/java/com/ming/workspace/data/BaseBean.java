package com.ming.workspace.data;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/8  11:44
 * desc   :
 * version: 1.0
 */
public class BaseBean {

    /**
     * data : {}
     * message : string
     * status : 0
     */

    private String msg;
    private int code;

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
}
