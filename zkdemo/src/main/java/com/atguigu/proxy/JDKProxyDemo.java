package com.atguigu.proxy;

import com.alibaba.dubbo.rpc.proxy.jdk.JdkProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Y
 * @create 2020-04-29 15:27
 */
public class JDKProxyDemo {
   public static  ZhangSan z3=new ZhangSan();

    public static void main(String[] args) {
        Person proxy = (Person)Proxy.newProxyInstance(z3.getClass().getClassLoader(), z3.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object invoke = method.invoke(z3, args);
                System.out.println("中介帮他找到了房子");
                return invoke;
            }
        });
        proxy.eat();
    }
}
