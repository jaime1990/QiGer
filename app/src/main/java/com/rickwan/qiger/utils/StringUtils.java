package com.rickwan.qiger.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {


    /**
     * 验证是否为手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if (mobiles.isEmpty() || mobiles.length() != 11) {
            return false;
        }

        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * 验证是否为手机号或座机号
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles) {
        if (mobiles.isEmpty()) {
            return false;
        }
        String regexp = "(^(0[0-9]{2,3}-)?\\d{6,8})|(1[2-9]\\d{9})";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * 验证是否为邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email.isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher m = p.matcher(email);

        return m.matches();
    }


    /**
     * 验证是否为身份证号
     * @param idCardNo
     * @return
     */
    public static  boolean isIdCard(String idCardNo){

        if (idCardNo.isEmpty()) {
            return false;
        }
        String regexp = "(^d{15}$)|(^d{17}([0-9]|X)$)";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(idCardNo);

        return m.matches();
    }


    /**
     * 验证是否为车牌号
     * @param carNo
     * @return
     */
    public static  boolean isCarNo(String carNo){

        if (carNo.isEmpty()) {
            return false;
        }
        String regexp = "[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z0-9]{5}";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(carNo);

        return m.matches();
    }

    /**
     * 保留两位有效数字
     * @param price
     * @return
     */
    public static String formatePrice(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);

    }

    public static SpannableString getPrice(double price) {


        String value = "￥" + formatePrice(price);
        SpannableString ss = new SpannableString(value);

            ss.setSpan(new RelativeSizeSpan(0.8f),0, 1,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return ss;

    }

    public static SpannableString getPriceSpannableString(double price) {


        String value = "￥" + formatePrice(price);
        SpannableString ss = new SpannableString(value);
        if (value.contains(".")) {
            String[] temp = value.split("\\.");
            ss.setSpan(new RelativeSizeSpan(1.6f), 1, temp[0].length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        return ss;

    }

    /**
     * 将str中包含的数字、"."标记为红色
     * @param value
     * @return
     */
    public static SpannableStringBuilder getText(String value ) {
        String strs = value.toString().trim();
        Pattern p = Pattern
                .compile("([0-9]\\d*\\.?\\d*\\%?)|(0\\.\\d*[0-9])\\%?");
        Matcher m = p.matcher(strs);
        SpannableStringBuilder style = new SpannableStringBuilder(strs);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            style.setSpan(new ForegroundColorSpan(Color.parseColor("#fb363f")), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        return style;
    }


}
