package Netty.AIO;

public class TimeServer {

	private static final int port = 8080;
	
	
	public static void main(String[] args) {
          
	      AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
	      new Thread(timeServer,"AIO-AsyncTimeServerHandler-001").start();
	
	}
	
	
	
	
}
