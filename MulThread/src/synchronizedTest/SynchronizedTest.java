package synchronizedTest;

/**
 * 使用Synchronized进行同步锁操作
 * @author Administrator
 *
 */
public class SynchronizedTest {

	private static String lock;

	public static void main(String[] args) {
//      1.直接使用对象作为对象锁		
//		lock = new String("lock");
//		for (int i = 0; i < 10; i++) {
//			new Thread(new ThreadTest1(lock)).start();
//		}
//      2.使用静态方式作为对象锁
		for (int i = 0; i < 10; i++) {
			new Thread(new ThreadTest2(i)).start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

	public static class ThreadTest1 implements Runnable {
		private String lock;
		public ThreadTest1(String lock){
			this.lock = lock;
		}
		@Override
		public void run() {
			synchronized (lock) {
				for (int i = 0; i < 100; i++) {
					System.out.println("threadNo:" + i);
					
				}
			}
		}
	}
	
	
	
	public static synchronized void executeStaticMethod(int threadNo){
		for (int i = 0; i < 100; i++) {
			System.out.println("threadNo:"+threadNo+" i:"+i);
		}
	}
	
    public static class ThreadTest2 implements Runnable{
       
    	private int threadNo;
    	
    	public ThreadTest2(int threadNo){
    		this.threadNo = threadNo;
    	}
    	
		@Override
		public void run() {
			executeStaticMethod(threadNo);
		}
    }
	
	
	
	

}
