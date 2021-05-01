package com.zp.demo.algorithm.designmode;

import com.zp.demo.Zlog;

import java.util.ArrayList;
import java.util.List;

/**
 * OKHttp 拦截器模式 - 模拟代码.
 */
public class InterceptorTest {

    public static void main(String[] args) {
        List<Interceptor> list = new ArrayList<>();
        list.add(new LogInterceptor());
        list.add(new RetryAndFollowUpInterceptor());
        list.add(new BridgeInterceptor());
        list.add(new CacheInterceptor());
        list.add(new ConnectionInterceptor());
        list.add(new CallServerInterceptor());
        Request request = new Request();
        RealInterceptorChain chain = new RealInterceptorChain(request, list, 0);
        chain.proceed(request);
        Zlog.log("main response");
    }
}

class RealInterceptorChain {

    public Request request;
    private List<Interceptor> interceptors;
    private int index;

    public RealInterceptorChain(Request request, List<Interceptor> interceptors, int index) {
        this.request = request;
        this.interceptors = interceptors;
        this.index = index;
    }

    private RealInterceptorChain copy(int index) {
        return new RealInterceptorChain(request, interceptors, index);
    }

    public Response proceed(Request request) {
        RealInterceptorChain next = copy(index + 1);
        Response response = interceptors.get(index).intercept(next);
        return response;
    }
}

class Request{}
class Response{}

interface Interceptor {
    public Response intercept(RealInterceptorChain chain);
}

class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(RealInterceptorChain chain) {
        Request request = chain.request;
        Zlog.log("LogInterceptor request:" + request);
        Response response = chain.proceed(request);
        Zlog.log("LogInterceptor response:" + request);
        return response;
    }
}

class RetryAndFollowUpInterceptor implements Interceptor {

    @Override
    public Response intercept(RealInterceptorChain chain) {
        Request request = chain.request;
        Zlog.log("RetryAndFollowUpInterceptor request:" + request);
        Response response = chain.proceed(request);
        Zlog.log("RetryAndFollowUpInterceptor response:" + request);
        return response;
    }
}

class BridgeInterceptor implements Interceptor {

    @Override
    public Response intercept(RealInterceptorChain chain) {
        Request request = chain.request;
        Zlog.log("BridgeInterceptor request:" + request);
        Response response = chain.proceed(request);
        Zlog.log("BridgeInterceptor response:" + request);
        return response;
    }
}

class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(RealInterceptorChain chain) {
        Request request = chain.request;
        Zlog.log("CacheInterceptor request:" + request);
        Response response = chain.proceed(request);
        Zlog.log("CacheInterceptor response:" + request);
        return response;
    }
}

class ConnectionInterceptor implements Interceptor {

    @Override
    public Response intercept(RealInterceptorChain chain) {
        Request request = chain.request;
        Zlog.log("ConnectionInterceptor request:" + request);
        Response response = chain.proceed(request);
        Zlog.log("ConnectionInterceptor response:" + request);
        return response;
    }
}

class CallServerInterceptor implements Interceptor {

    @Override
    public Response intercept(RealInterceptorChain chain) {
        Request request = chain.request;
        Zlog.log("CallServerInterceptor request:" + request);
        Response response = new Response();
        Zlog.log("CallServerInterceptor response:" + request);
        return response;
    }
}