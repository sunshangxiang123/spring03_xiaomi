package com.qf.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5Utils {
    public static String md5(String str){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("Md5");
            messageDigest.update(str.getBytes("utf-8"));
            byte[] digist = messageDigest.digest();
            BigInteger bigInteger = new BigInteger(1, digist);  //将BigInteger的符号大小表示形式转换为BigInteger。
            String secret = bigInteger.toString(16);  //16进制
            //System.out.println(secret);
            return secret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
