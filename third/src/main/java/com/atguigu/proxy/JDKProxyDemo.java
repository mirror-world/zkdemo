package com.atguigu.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Y
 * @create 2020-04-29 15:27
 */
public class JDKProxyDemo {

    public static Object getJdkProxy(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object invoke = method.invoke(target, args);
                System.out.println("物竞天择");
                return invoke;
            }
        });
    }

    public static void main(String[] args) {
        ZhangSan z3 = new ZhangSan();
        Person p1 = (Person)getJdkProxy(z3);
        p1.eat();
    }
}
