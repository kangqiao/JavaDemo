//package com.zp.demo;
//
//import com.huobi.wallet.pro.common.infra.web.ApiResult;
//import com.huobi.wallet.pro.common.util.Mappers;
//import com.huobi.wallet.pro.common.util.RequestUtils;
//import com.huobi.wallet.pro.common.util.Texts;
//import com.huobi.wallet.pro.common.util.Times;
//import com.huobi.wallet.pro.gateway.config.SignatureConfig;
//import com.netflix.zuul.context.RequestContext;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import javax.servlet.http.HttpServletRequest;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//
//public class Test {
//    public static void main(String[] args) {
//        test1();
////        test2();
//
//
//    }
//
//    private static void test2() {
//        Test test = new Test();
//
////        long epochMilli = 1629980967754L;
////        long lng = epochMilli / 3000000;
//
//
//        Mac hmacSha256 = test.getMac();
//        String payload1 ="GET/notice/version/checkMTYyOTk4MDk2NDM5MTo2YzcyYzgyMjljNmE1ZDQ0YzI4MmE0NDI3OWFmZjRiNzY2ODQ2NTNmMDg1ODZkM2E2OTM1NjQ3ZmE0MjQ4ZTQzOkJCRjlDNkEzLUU5NUYtODUyNS1BMjUxLUZDQ0U4OTc4RTE5OC0yMzQ2LTMyNUEtMDhBMC1EMjZFODc4QTIwQTE6ZDE3NGQwNjVlMjNkZTQxMjBmYTJjYTc2ZWZhMzEzNmI0NGRkM2RkY2RmNTFhMzYxZWQ0NWQzNTkyM2NkNDE4YTYwNGJkZWU2MGYzZmI0NTAyOGQyYjZlNWViYzJkNzMxYmE5NDcyYzI3OTUyNDcwNWIxNzY3NmMwZjZjZDZhODQ=543326channel=033000000X&language=zh_CN&platform=android&versionNumber=v2.8.6.001";
//        String actualSign1 = test.signature(payload1, hmacSha256);
//        System.out.println("actualSign1 : "+actualSign1);
//    }
//
//    private static void test1() {
//        /**
//         *bad-signature
//         *  payload:GET/notice/reg/device
//         *  MTYyOTk3NTMzNDQyODo1MGYwOGJjZmU1NWUzNzhlZGEzZTg0ZTQ0MDJhMGZiYzk4Yzg1NTMxNWZhMjZhNDk1ZTAwM2E0YWZhZDQ1ZDZkOkY0MDJGQTgzLTlCRjAtNjg2Qy1GM0RELUY2OUQ3MDk5NThERC0zQzVGLUI4ODEtQTBBQS01MEQ2MDA4MDAzNkI6ZTFiM2M3NDEwMTUwZTk1NWYzYjkzNGI3NTEzNGNkZTcyYmYwZjhhNWU0ODkyMzdkMTIxMzJhYzE5NGYwZDlmYzczMmM3ZDIyZGQxNzRhMmVjYzdiMGY5NzA2YTM4YmZiZDFmZDJmNjYwY2EzYzVlMmEyNDhjMzJjNjRmNzE1Nzc=
//         *  543326
//         *  actualSign:omVV+WKFxyt1c0xjeVZl1v/0heSsyOumPebLs3cNP9Q=
//         *  signature:MFcb3Fk5WfGrXuHdEyLslBY4BV2qbfmd1O+nJktLzHQ=
//         *  epochMilli:1629975335446
//         */
//        Test test = new Test();
//
////        long epochMilli = 1629952839698L;
////        long lng = epochMilli / 3000000;
//
//
//        Mac hmacSha256 = test.getMac();
//        String payload1 = "GET/notice/version/checkMTYyOTk1MjgzODc3OTo5MjIwYWYzOTNkMTUzYzNiYmNkNmQ4ZGVlZjkxNTFiN2FhNTJmNWI4ZTM3MDQ0M2Q1N2I1NzQ2YjRkZWMyYzJkOjc4Mjk2QTAyLThFMTctRUExOS03NUUxLTNDMkVEOUMwNUNCQS1FMkMwLThFREItRkM4RS1BNEU5NjJFNDVEQUM6NTU4MGY2ODAyNjYwODMxOWQzNjIyMzllNWQwZjNmM2YwNGFjZDk1ZjFkYWFjMGY2ZmQzMTBjZjgxNDI3NzA0YTcyZTJkOWUyZGIwY2JlNTZhOGQ0YjM1Zjc4N2M5NDM0ZDA0NjdlMjhjYTRhMWNjMDRkZDJhZTEzMDU0ZGIxNzg=543318channel=0330320053&language=zh_CN&platform=android&versionNumber=v2.9.0.032";
//                           GET/notice/version/checkMTYyOTk1MjgzODc3OTo5MjIwYWYzOTNkMTUzYzNiYmNkNmQ4ZGVlZjkxNTFiN2FhNTJmNWI4ZTM3MDQ0M2Q1N2I1NzQ2YjRkZWMyYzJkOjc4Mjk2QTAyLThFMTctRUExOS03NUUxLTNDMkVEOUMwNUNCQS1FMkMwLThFREItRkM4RS1BNEU5NjJFNDVEQUM6NTU4MGY2ODAyNjYwODMxOWQzNjIyMzllNWQwZjNmM2YwNGFjZDk1ZjFkYWFjMGY2ZmQzMTBjZjgxNDI3NzA0YTcyZTJkOWUyZGIwY2JlNTZhOGQ0YjM1Zjc4N2M5NDM0ZDA0NjdlMjhjYTRhMWNjMDRkZDJhZTEzMDU0ZGIxNzg=543318channel=0330320053&language=zh_CN&platform=android&versionNumber=v2.9.0.032
//        String actualSign1 = test.signature(payload1, hmacSha256);
//        System.out.println("actualSign1 : "+actualSign1);
//
//
//        String payload2 = "GET/notice/version/checkMTYyOTk1MjgzODc3OTo5MjIwYWYzOTNkMTUzYzNiYmNkNmQ4ZGVlZjkxNTFiN2FhNTJmNWI4ZTM3MDQ0M2Q1N2I1NzQ2YjRkZWMyYzJkOjc4Mjk2QTAyLThFMTctRUExOS03NUUxLTNDMkVEOUMwNUNCQS1FMkMwLThFREItRkM4RS1BNEU5NjJFNDVEQUM6NTU4MGY2ODAyNjYwODMxOWQzNjIyMzllNWQwZjNmM2YwNGFjZDk1ZjFkYWFjMGY2ZmQzMTBjZjgxNDI3NzA0YTcyZTJkOWUyZGIwY2JlNTZhOGQ0YjM1Zjc4N2M5NDM0ZDA0NjdlMjhjYTRhMWNjMDRkZDJhZTEzMDU0ZGIxNzg=543317channel=0330320053&language=zh_CN&platform=android&versionNumber=v2.9.0.032";
//        String actualSign2 = test.signature(payload2, hmacSha256);
//        System.out.println("actualSign2 : "+actualSign2);
//
//
//        String payload3 = "GET/notice/version/checkMTYyOTk1MjgzODc3OTo5MjIwYWYzOTNkMTUzYzNiYmNkNmQ4ZGVlZjkxNTFiN2FhNTJmNWI4ZTM3MDQ0M2Q1N2I1NzQ2YjRkZWMyYzJkOjc4Mjk2QTAyLThFMTctRUExOS03NUUxLTNDMkVEOUMwNUNCQS1FMkMwLThFREItRkM4RS1BNEU5NjJFNDVEQUM6NTU4MGY2ODAyNjYwODMxOWQzNjIyMzllNWQwZjNmM2YwNGFjZDk1ZjFkYWFjMGY2ZmQzMTBjZjgxNDI3NzA0YTcyZTJkOWUyZGIwY2JlNTZhOGQ0YjM1Zjc4N2M5NDM0ZDA0NjdlMjhjYTRhMWNjMDRkZDJhZTEzMDU0ZGIxNzg=543319channel=0330320053&language=zh_CN&platform=android&versionNumber=v2.9.0.032";
//        String actualSign3 = test.signature(payload3, hmacSha256);
//        System.out.println("actualSign3 : "+actualSign3);
//
//        System.out.println("signature : "+"MFcb3Fk5WfGrXuHdEyLslBY4BV2qbfmd1O+nJktLzHQ=");
//    }
//
//    private Mac getMac() {
//        Mac hmacSha256;
//        try {
//            hmacSha256 = Mac.getInstance("HmacSHA256");
//            SecretKeySpec secKey = new SecretKeySpec("KEW4SV4UyYQ04DNsiPeSL1RR2cChrjaadmScQzgyGTLwLsMkc6YipLRVKW29s5Zy".getBytes(StandardCharsets.UTF_8),
//                    "HmacSHA256");
//            hmacSha256.init(secKey);
//        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
//            throw new RuntimeException(e);
//        }
//        return hmacSha256;
//    }
//
//    protected String signature(String payload, Mac hmacSha256) {
//
//        byte[] hash = hmacSha256.doFinal(payload.getBytes(StandardCharsets.UTF_8));
//
//        String toHex = Texts.base64Encode(hash);
////        System.out.println("payload "+payload);
////        System.out.println("toHex "+toHex);
//
//        return toHex;
//    }
//}
