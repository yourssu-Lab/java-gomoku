package com.yourssu.proxy;

public class AImplProxy implements A{

    private final A a = new AImpl();

    @Override
    public void sendMessage() {
        System.out.println("AImplProxy Previous");
        a.sendMessage();
        System.out.println("AImplProxy Later");
    }
}
