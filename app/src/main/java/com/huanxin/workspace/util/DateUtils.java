package com.huanxin.workspace.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


    /**
     * 获取时间戳
     * 输出结果:1438692801766
     */
    public void getTimeStamp() {
        Date date = new Date();
        long times = date.getTime();
        System.out.println(times);

        //第二种方法：
        new Date().getTime();
    }

    /**
     * 获取格式化的时间
     * 输出格式：2020-07-21 20:55:35
     */
    public void getFormatDate() {
        Date date = new Date();
        long times = date.getTime();//时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        System.out.println(dateString);
    }

    /**
     * 将时间戳转化为标准时间
     * 输出：Tue Oct 20 12:04:36 CST 2020
     */
    public static String timestampToDate(Long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}