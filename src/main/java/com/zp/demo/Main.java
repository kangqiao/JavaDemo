package com.zp.demo;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;

public class Main {

    String a = "a";

    public static void main(String[] args) {
//        System.out.println(~0);
//        Main main = new Main();
//
//        run(main);
//        main.a = null;
//        main = null;
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//
//        Zlog.log("" + calcSpeed(123456, 233));
//        Zlog.log("" + calcSpeed(123, 2));
//        Zlog.log("" + calcSpeed(-11, 234));
//        Zlog.log("" + calcSpeed(-11234567, 234));
//        Zlog.log("" + calcSpeed(0, 0));
//        Zlog.log("" + calcSpeed(98765, 0));
//        Zlog.log("" + calcSpeed(987654321, 234467238));
//        Zlog.log("" + calcSpeed(Long.MAX_VALUE, Long.MIN_VALUE));
        //byteCount=25159, rctTimeNanos=169795677

        //Zlog.log("" + (123 / 100f));

//        Zlog.log("" + calcSpeed(25159, 516 - 378));
//        Zlog.log("" + calcSpeed(25159, 1169795677));
//
//        Zlog.log(Runnable.class.getCanonicalName());

//        String bstr = new String("asgdhfjg我".getBytes(), Charset.defaultCharset());
//        Zlog.log("" + bstr.length());
//        Zlog.log("" + bstr.getBytes().length);
//        int l = String.valueOf('c').getBytes().length;
//        Zlog.log(""+l);
//        int l2 = String.valueOf('我').getBytes().length;
//        Zlog.log(""+l2);
//
//        int arr[] = new int[]{12, 1, 12, 3, 12, 1, 1, 2, 3,3};
//        System.out.println("getSingle = " + getSingle(arr, arr.length));
//        arr = new int[]{10, 20, 10, 30, 10, 30, 30};
//        System.out.println("getSingle = " + getSingle(arr, arr.length));


//        StringBuilder sb = new StringBuilder();
//        sb.append("aaa").append(",").append("bbb").append(",");
//        sb.deleteCharAt(sb.length()-1);
//        System.out.println(sb.toString());
//
//        System.out.println("===============");
//        String str1 = new StringBuilder("计算机").append("软件").toString();
//        System.out.println(str1.intern() == str1);
//
//        String str2 = new StringBuilder("ja").append("va").toString();
//        System.out.println(str2.intern() == str2);


        String source = "function wrapTime(time) {\n" +
                "        var totalTime = time.loadEventEnd - time.navigationStart;\n" +
                "        var rr = JSON.parse(1.9);\n" +
                "        if(totalTime > 5000) {\n" +
                "            rr=0.8;\n" +
                "        } else if(totalTime > 2000) {\n" +
                "            rr=0.9;\n" +
                "        }" +
                "}";

        double b = 1.023;
        source = source.replaceFirst("JSON\\.parse\\(\\d+\\.?\\d+?\\)", "JSON.parse("+b+")");
        System.out.println(source);
    }

    private <T> void genericMethod(T t) {
        List<T> list = new ArrayList<T>();

    }

    /**
     * 计算数据传输速度
     * @param byteCount
     * @param timeMillis
     * @return
     */
    private static final double factor = 1024d / 1000d;
    public static double calcSpeed(long byteCount, long timeMillis) {
        if (timeMillis <= 0 || byteCount <= 0) return 0;
        double ret = byteCount / timeMillis / factor;
        return Math.round(ret * 100) / 100d;
    }

    private static void run(Main main) {
        try {
            Thread.sleep(1000);
            new Thread(() -> {
                System.out.println(main.a.equals("a"));
            }).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void httpSSL() throws IOException {
        final URL htmlUrl = new URL("https://1.1.1.1/");
        HttpsURLConnection connection = (HttpsURLConnection) htmlUrl.openConnection();
        connection.setRequestProperty("Host","www.meipai.com");
        connection.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return HttpsURLConnection.getDefaultHostnameVerifier()
                        .verify("www.meipai.com",session);
            }
        });

        InetAddress.getAllByName("www.meipai.com");
    }

    /**
     * 输入: arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3,3}
     * 输出: 2
     * 除了2其它都出现3次。
     *
     * 输入: arr[] = {10, 20, 10, 30, 10, 30, 30}
     * 输出: 20
     * 除了20其它都出现3次。
     * @param arr
     * @param n
     * @return
     */
    static int getSingle(int arr[], int n) {
        Set<Integer> set = new HashSet<>();
        int sum = 0, sumInner = 0;
        for (int i=0; i<n; i++) {
            if (!set.contains(arr[i])) {
                set.add(arr[i]);
                sumInner += arr[i];
            }
            sum += arr[i];
        }
        return Math.abs(sum - sumInner - sumInner - sumInner) / 2;
    }

    public void test111() {}
    static void test() {
        Main main = new Main() {
            @Override
            public void test111() {
                super.test111();
            }
        };
    }
}
