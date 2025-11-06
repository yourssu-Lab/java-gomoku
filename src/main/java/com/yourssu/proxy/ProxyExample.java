package com.yourssu.proxy;

public class ProxyExample {
        static class TransactionManager {
            public void begin() {
                System.out.println("[Tx] begin");
            }
            public void commit() {
                System.out.println("[Tx] commit");
            }
            public void rollback() {
                System.out.println("[Tx] rollback");
            }
        }
}
