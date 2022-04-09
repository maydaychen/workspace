package com.huanxin.workspace.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:45
 * desc   :
 * version: 1.0
 */
public class CheckUtil {
    private static Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    /**
     * Check email boolean.
     *
     * @param eMail 用户输入的电子邮件地址
     * @return 电子邮件地址是否正确
     */
    public static boolean checkEmail(String eMail) {
        Matcher mc = pattern.matcher(eMail);
        return mc.matches();
    }
}
