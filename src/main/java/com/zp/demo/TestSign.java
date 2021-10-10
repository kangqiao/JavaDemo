package com.zp.demo;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class TestSign {

    static String host = "";

    public static void main(String[] args) {

        long timeMill = 1631864235403l;
        long time = timeMill/3000000;
        System.out.println(testSign(time));
        System.out.println(testSign(time+1));
        System.out.println(testSign(time-1));

//        System.out.println(time + "=> " + testSign(time));
//        System.out.println(time + "=> " + testSign(time-1));
//        System.out.println(time + "=> " + testSign(time+1));
//        System.out.println(time + "=> " + testSign(time-2));
//        System.out.println(time + "=> " + testSign(time+2));
//        host = "https://api.schoolbuy.top";
//        System.out.println(time + "=> " + testSign(543494));
//        System.out.println(time + "=> " + testSign(543492));
//        System.out.println(time + "=> " + testSign(543493));
//        System.out.println(time + "=> " + testSign(543495));
//        System.out.println(time + "=> " + testSign(543496));
//        host = "https://api.trustapiapp.com";
//        System.out.println(time + "=> " + testSign(543494));
//        System.out.println(time + "=> " + testSign(543492));
//        System.out.println(time + "=> " + testSign(543493));
//        System.out.println(time + "=> " + testSign(543495));
//        System.out.println(time + "=> " + testSign(543496));
//        host = "https://api.lzmty.net";
//        System.out.println(time + "=> " + testSign(543494));
//        System.out.println(time + "=> " + testSign(543492));
//        System.out.println(time + "=> " + testSign(543493));
//        System.out.println(time + "=> " + testSign(543495));
//        System.out.println(time + "=> " + testSign(543496));
//        host = "https://api.huobiwalletapi.com";
//        System.out.println(time + "=> " + testSign(543494));
//        System.out.println(time + "=> " + testSign(543492));
//        System.out.println(time + "=> " + testSign(543493));
//        System.out.println(time + "=> " + testSign(543495));
//        System.out.println(time + "=> " + testSign(543496));
//        host = "https://api.quickwallet.io";
//        System.out.println(time + "=> " + testSign(543494));
//        System.out.println(time + "=> " + testSign(543492));
//        System.out.println(time + "=> " + testSign(543493));
//        System.out.println(time + "=> " + testSign(543495));
//        System.out.println(time + "=> " + testSign(543496));
//        host = "https://api.hbwallet.net";
//        System.out.println(time + "=> " + testSign(543494));
//        System.out.println(time + "=> " + testSign(543492));
//        System.out.println(time + "=> " + testSign(543493));
//        System.out.println(time + "=> " + testSign(543495));
//        System.out.println(time + "=> " + testSign(543496));
//        host = "https://api4.hbwallet.net";
//        System.out.println(time + "=> " + testSign(543494));
//        System.out.println(time + "=> " + testSign(543492));
//        System.out.println(time + "=> " + testSign(543493));
//        System.out.println(time + "=> " + testSign(543495));
//        System.out.println(time + "=> " + testSign(543496));
//        host = "https://api.hbwalletapi.net";
//        System.out.println(time + "=> " + testSign(543494));
//        System.out.println(time + "=> " + testSign(543492));
//        System.out.println(time + "=> " + testSign(543493));
//        System.out.println(time + "=> " + testSign(543495));
//        System.out.println(time + "=> " + testSign(543496));

//        String result = "VZx63ZoghmM2pIM1DHcqKLhLtAiDtXk+6uJa9Iz0fGg=";
//        System.out.println(testSign(time));
//        for (int i = 0; i<100000; i++) {
//            String sign = testSign(time + i);
//            if (result.equalsIgnoreCase(sign))  {
//                System.out.println((time+i) + " => " + sign);
//            }
//        }
//
//        System.out.println("====================");
//
//        for (int i = 0; i<100000; i++) {
//            String sign = testSign(time - i);
//            if (result.equalsIgnoreCase(sign))  {
//                System.out.println((time-i) + " => " + sign);
//            }
//        }

    }
    //ldvglKrvq7rng1jGxxOxu7MJgCWq7nRkxjsYmkd%2BY64%3D

    //https://api.trustapiapp.com/apis/v1/popup/promotion?channel=AndroidGuanwang&version=2.9.1.033&language=zh
    //JFVh5C6DdIwnHTVm3ChjZhzv72HgcneK%2F8Ys2UYAJtY%3D
    public static String testSign(long time) {
//        String payload = "GETapis/v1/popup/promotionMTYzMDQ1NzcwOTMzOTo1YmFhMjJhYTVmZmFjZTI2YjZhMjExM2YyMzE5NWIwZTFiZjg2MDkwYzZhZDNhMTMzMzJjOGVmN2Y3NDEzMDU5Ojk5NDA3N0MzLTZCQTgtNEFCQi0zRTYwLTBDQjI2NUFDMDkzRi04Nzc5LUQwQjAtOEM3Qi1BMkNERUIxNkY5Mzc6Yzc0NGQ4NjVjODkwYzAwYzAwMmFiMjEzMWI5MjIzNzMzMmNjN2JlOTZkYmEzMDI3YTAyMTZmZGQwOTdiYTJlN2UzZDA3M2UxZmU0YzE1ZjkzZThjNTE5NDdiNGI0NDYxMjZjZjFkYzE4YmNjMDRjYmUwYjYyZDQwNjI1YTk5MTA="+time+"channel=AndroidGuanwang&language=zh&version=2.9.1.033";
        String method = "GET";
        String path = "/notice/version/check";
        String session = "MTYzMTgzNDg0OTQwNDo2NGFhZTU5MDRlZmU0YTA3YmFjZWRjMzI2NWJmZjgyOGQ0MjZkMmJmYzE5NzIyYWYxOWRjMTFmMDcyMzUyMmFmOkE5NzU4MDVFLTIxMkQtMDc1OS0yOTQ0LUQ1MjEyODQwNjdEQy03NDU3LUM2MUUtN0FEOS0wMzY3Q0UyNTc2REQ6OWU5YzI5MjQxZjA3NTYyNWQwYWFkZWI5MzkzYTRkMzAzMGVhMmZiMmQzZDgyZTkzMGViM2NhNTM1ZjY2OWYyMmJhOTk0YjhjNzk4Mzk1ODRkOGI2MTlkOGI1MGNiOTJiMWQ5ODg1ZDNkYWZjZDdkY2NkNDYyZDI3MjI5YWI5OGU=";
        String params = "channel=0330320060&versionNumber=v2.9.6.002&platform=android";

        return sign(method, path, session, time, params);
    }

    private static String sign(String method, String path, String session, long time, String params) {
        String payload = new StringBuilder().append(method)
                .append(path)
                .append(session)
                .append(time)
                .append(params)
                .toString();
        System.out.println(payload);
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec("KEW4SV4UyYQ04DNsiPeSL1RR2cChrjaadmScQzgyGTLwLsMkc6YipLRVKW29s5Zy".getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String signText = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(payload.getBytes()));

            System.out.println(payload + " =>>>>> " + signText);
            return signText;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
