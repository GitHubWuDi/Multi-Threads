package synchronizedTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import synchronizedTest.VolatileTest.SaveMoneyThread;

public class ReentrantLockTest {

	  private static Lock lock = new  ReentrantLock();
	
	  private static int count = 0;
	  
	  
	  public static void saveMoney(int money){
		  lock.lock();//上锁
		  try{
			  count+=money;
			  System.out.println(System.currentTimeMillis() + "存进：" + money); 
		  }finally{
			  lock.unlock();//解锁
		  }
		  
		  
	  }
	  
	  
	  public static void plusMoney(int money){
		  lock.lock();
		  try{
			  if (count - money < 0) {  
		            System.out.println("余额不足");  
		            return;  
		        }  
			  count-=money;
			  System.out.println(System.currentTimeMillis() + "消费：" + money); 
		  }finally{
			  lock.unlock();//解锁
		  }
	  }
	  
	  
	  public static void lookMoney(){
		  System.out.println("账户余额："+count);
		  
	  }
	  
	  public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {					
					saveMoney(1000);
					lookMoney();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {					
					plusMoney(50);
					lookMoney();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	  
	  
	  
	  
	
	
}
