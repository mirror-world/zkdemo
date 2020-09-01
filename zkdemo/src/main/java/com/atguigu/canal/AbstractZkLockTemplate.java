package com.atguigu.canal;


import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * @author Y
 * @create 2020-04-26 16:11
 */
public abstract class AbstractZkLockTemplate implements ZkLock {
    //第一次
    private final static String ZKSERVER="127.0.0.1:2181";
    private final static int TIME_OUT=60;
    public static String path="sale";
    ZkClient zkClient=new ZkClient(ZKSERVER,TIME_OUT);
    CountDownLatch countDownLatch=null;
    public void zkLock() {
        if (tryLock()){
            System.out.println(Thread.currentThread().getName()+"\t 占用锁");
        }else{
        waitLock();
         zkLock();
        }
    }

     public abstract void waitLock();

    public abstract boolean tryLock();

    public void zkUnlock() {
    if (zkClient!=null){
      zkClient.close();
    }
        System.out.println(Thread.currentThread().getName()+"\t 释放锁");
    }
}
