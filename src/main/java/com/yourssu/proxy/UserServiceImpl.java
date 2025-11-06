package com.yourssu.proxy;

class UserServiceImpl implements UserService {

    @Override
    public void createUser(String name) {
        System.out.println("UserServiceImpl.createUser: " + name);
        if ("error".equals(name)) {
            throw new RuntimeException("DB error while creating " + name);
        }
    }

    @Override
    public void deleteUser(String name) {
        System.out.println("UserServiceImpl.deleteUser: " + name);
    }

}
