package com.yourssu;

import com.yourssu.proxy.A;
import com.yourssu.proxy.AImpl;
import com.yourssu.proxy.CustomInvocationHandler;

import java.lang.reflect.Proxy;

public class Application {
    public static void main(String[] args) {
        A a = new AImpl();
        A proxy = (A) Proxy.newProxyInstance(
                A.class.getClassLoader(),
                new Class[]{A.class},
                new CustomInvocationHandler(a)
        );

        proxy.sendMessage();
    }
}
