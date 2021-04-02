package com.zmj.commom;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Random;

@Component
public class BaseUtils {

    public String generate(String password) {
        password = md5Hex(password + getRandomSalt());
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = getRandomSalt().charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * 生成随机盐
     */
    public String getRandomSalt() {
//        Random r = new Random();
//        StringBuilder sb = new StringBuilder(16);
//        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
//        int len = sb.length();
//        if (len < 16) {
//            for (int i = 0; i < 16 - len; i++) {
//                sb.append("0");
//            }
//        }
//        return sb.toString();
        return "zhaomeijing10324";
    }

    /**
     * 校验密码是否正确
     */
    public boolean verify(String password, String md5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(new String(cs1));
    }
    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    public String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }
//
//    public static void main(String[] args) {
//        System.out.println(new BaseUtils().generate("123456").length());
//    }
}
