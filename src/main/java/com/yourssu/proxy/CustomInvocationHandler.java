package com.yourssu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {

    private final Object target;

    public CustomInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // method는 인터페이스의 메서드 (annotation이 여기에 있음)
        boolean isAnnotated = method.isAnnotationPresent(ProxyAnnotation.class);

        if (isAnnotated) {
            System.out.println("ProxyAnnotation prev");
        }

        // 실제 실행은 target 객체의 메서드로
        Object result = method.invoke(target, args);

        if (isAnnotated) {
            System.out.println("ProxyAnnotation later");
        }

        return result;
    }
}
