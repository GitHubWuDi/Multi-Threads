package synchronizedTest;

import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {
       
	   private int value;
	
	    public static void main(String[] args) {
	    	
//	    	AtomicInteger value = new AtomicInteger();
//	    	value.incrementAndGet();
//	    	int i = value.get();
//	    	boolean compareAndSet = value.compareAndSet(i, 1);
//	    	System.out.println(compareAndSet);
//	    	System.out.println(value);
	    	long start = System.currentTimeMillis();  
	    	CASTest casTest = new CASTest(0);
	    	for (int i = 0; i < 100000000; i++) {
	    		casTest.increase();
			}
	    	long end = System.currentTimeMillis();  
	    	System.out.println("time synchronized elapse:"+(end-start));
	    	//**************使用CAS完成对应的添加****************//
	    	long start1 = System.currentTimeMillis();  
	    	AtomicInteger atomicInteger = new AtomicInteger();
	    	for (int i = 0; i < 100000000; i++) {
	    		atomicInteger.incrementAndGet();
			}
	    	long end1 = System.currentTimeMillis();  
	        System.out.println("time atomic elapse:"+(end1 -start1) ); 
	    	
		}
	    
	    public CASTest(int value){
	    	this.value = value;
	    }
	    
	    public synchronized int increase(){
	    	return value++;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	   
	
	    
}
