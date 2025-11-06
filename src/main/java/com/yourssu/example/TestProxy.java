package com.yourssu.example;

public class TestProxy implements Test{
    private Test test = new TestImpl();

    @Override
    public void test() {
        System.out.println("before test");
        this.test.test();
        System.out.println("after test");
    }
}
