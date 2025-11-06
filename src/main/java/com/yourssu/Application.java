package com.yourssu;

import com.yourssu.proxy.A;
import com.yourssu.proxy.AImplProxy;

public class Application {
    public static void main(String[] args) {
        A a = new AImplProxy();
        a.sendMessage();
    }
}
