package synchronizedTest;


public class VolatileTest {
      
	private static volatile int account = 1000; //初始值为1000

	public static int getAccount() {
		return account;
	}
	
	
	public static void save(int money){
		account+=money;
		System.out.println(+System.currentTimeMillis() +"余额："+account);
	}
	
	
	public static void plus(int money){
		if(account-money<0){
			System.out.println("余额不足");
			return;
		}
		account-=money;
		System.out.println(+System.currentTimeMillis() + "取出：" + money+"余额："+account);
	}
	
	public static class SaveMoneyThread implements Runnable{
       private int money;
       public SaveMoneyThread(int money){
    	   this.money = money;
       }
		@Override
		public void run() {
			save(money);
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
				plus(money);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new SaveMoneyThread(100)).start();
		new Thread(new PlusMoneyThread(300)).start();
		
	}
	
	
	
	
	
}
