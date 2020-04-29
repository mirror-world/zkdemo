package com.atguigu.canal;

import org.I0Itec.zkclient.IZkDataListener;

/**
 * @author Y
 * @create 2020-04-26 16:26
 */
public class ZkDistrbuteLock extends AbstractZkLockTemplate {
    @Override
    public void waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {

            }

            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch!=null){
                    countDownLatch.countDown();
                }
            }
        };
        zkClient.subscribeDataChanges(path,iZkDataListener);
        if (zkClient.exists(path)){
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        zkClient.unsubscribeDataChanges(path,iZkDataListener);
    }

    @Override
    public boolean tryLock() {
        try {
            zkClient.createEphemeral(path);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }

    }
}
