package com.atguigu.canal;

/**
 * @author Y
 * @create 2020-04-26 15:57
 */
public interface ZkLock {
  //测试二次
  public  void zkLock();
  public void zkUnlock();
}
