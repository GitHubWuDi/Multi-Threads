package synchronizedTest;

/**
 * 如果使用ThreadLocal管理变量，则每一个使用该变量的线程都获得该变量的副本，副本之间相互独立，这样每一个线程都可以随意修改自己的变 量副本，而不会对其他线程产生影响。
 * 原来每个线程运行的都是一个副本，也就是说存钱和取钱是两个账户，知识名字相同而已。所以就会发生上面 的效果。
 * @author Administrator
 *
 */
public class ThreadLocalTest2 {

	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			return 0;
		};
		
	};
	
	public static void saveMoney(int money){
		threadLocal.set(threadLocal.get()+money);
		System.out.println(System.currentTimeMillis()+"存进："+money);
		lookMoney();
		
	}
	
	public static void plusMoney(int money){
		Integer lastmoney = threadLocal.get()-money;
		if(lastmoney<0){
			System.out.println("余额不够");
			return;
		}else{
			threadLocal.set(lastmoney);
			System.out.println(System.currentTimeMillis()+"花费："+money); 
			lookMoney();
		}
	}
	
	
	public static void lookMoney(){
		System.out.println("目前金额是："+threadLocal.get());
	}
	
	
	
	public static class SaveMoneyThread implements Runnable{
       private int money;
       public SaveMoneyThread(int money){
    	   this.money = money;
       }
		
		@Override
		public void run() {
			int i = 0;
			while(i<10){
				saveMoney(money);
				i++;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static class PlusMoneyThread implements Runnable{
        private int money;
        public PlusMoneyThread(int money){
        	this.money = money;
        }
		
		@Override
		public void run() {
			int i = 0;
			while(i<10){
				plusMoney(money);
				i++;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		new Thread(new SaveMoneyThread(200)).start() ;
		new Thread(new PlusMoneyThread(100)).start();
	}
	
	
	
	
	
	
}
