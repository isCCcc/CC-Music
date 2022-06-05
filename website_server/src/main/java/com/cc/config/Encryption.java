package com.cc.config;

import java.util.Base64;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : WZX
 * @create 2022/5/22 22:09
 * @Description : 自定义加密解密算法
 */
public class Encryption {

    // 加密和解密只要offset一致即可
    public static final int offset = Integer.MIN_VALUE + 10086;

    // 加密
    public static String encryption(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append("" + (char)(((int)s.charAt(i)) + offset));
        }
        return new StringBuilder(Base64.getEncoder().encodeToString(new String(sb.toString()).getBytes())).reverse().toString();
    }

    // 解密
    public static String decryption(String s) {
        s = new String(Base64.getDecoder().decode(new StringBuilder(s).reverse().toString()));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append((char)(((int)s.charAt(i)) - offset));
        }
        return sb.toString();
    }

    // public static void main(String[] args) {
    //     String str = "加密字符串";
    //     // 使用的时候可嵌套多层，嵌套越多密文越长
    //     System.out.println(encryption(encryption(str)));
    //     System.out.println(decryption(decryption(encryption(encryption(str)))));
    // }
}
