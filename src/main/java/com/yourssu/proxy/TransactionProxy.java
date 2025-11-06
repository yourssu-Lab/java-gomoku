package com.yourssu.proxy;

import com.yourssu.proxy.ProxyExample.TransactionManager;

class TransactionProxy implements UserService {
    private final UserService target;
    private final TransactionManager txManager;

    public TransactionProxy(UserService target, TransactionManager txManager) {
        this.target = target;
        this.txManager = txManager;
    }

    @Override
    public void createUser(String name) {
        txManager.begin();
        try {
            target.createUser(name);
            txManager.commit();
        } catch (RuntimeException ex) {
            txManager.rollback();
            throw ex;
        }
    }

    @Override
    public void deleteUser(String name) {
        txManager.begin();
        try {
            target.deleteUser(name);
            txManager.commit();
        } catch (RuntimeException ex) {
            txManager.rollback();
            throw ex;
        }
    }

}
