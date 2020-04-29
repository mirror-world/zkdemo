package com.atguigu.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Y
 * @create 2020-04-29 15:46
 */
public class CglibProxyDemo {

    private static Object getCGlibProxy(Object target){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object invoke = method.invoke(target, objects);
                System.out.println("中介帮忙介绍房子");
                return invoke;
            }
        });
        return enhancer.create();
    }

    public static void main(String[] args) {
        Person z3 = new ZhangSan();
       Person p1=(Person) getCGlibProxy(z3);
       p1.eat();
    }
}
