package executorofthread;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorTest {

	public static void main(String[] args) {

	}

	/**
	 * execute(Runnable)
	 */
	public void executeRunnable() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.execute(new Runnable() {
			public void run() {
				System.out.println("executeRunnable task");
			}

		});
	}
   /**
    * executeSubmit功能与executeRunnable类似，但是会返回一个future对象
    */
	public void executeSubmit(){
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		Future<?> future = executorService.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("executeSubmit task"); 	
			}
		});
		try {
			Object object = future.get();
			System.out.println("future:"+object);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 方法 submit(Callable) 和方法 submit(Runnable) 比较类似，但是区别则在于它们接收不同的参数类型。
	 * Callable 的实例与 Runnable 的实例很类似，但是 Callable 的 call() 方法可以返回壹個结果。
	 * 方法 Runnable.run() 则不能返回结果。
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public void executeSubmitCallable() throws InterruptedException, ExecutionException{
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		Future future = executorService.submit(new Callable() {
			@Override
			public Object call() throws Exception {
				return "return executeSubmitCallable";
			}
		
		});
		System.out.println("executeSubmitCallable:"+future.get());
	}
	
	/**
	 * 方法 invokeAny() 接收壹個包含 Callable 对象的集合作为参数。
	 * 调用该方法不会返回 Future 对象，而是返回集合中某壹個 Callable 对象的结果，而且无法保证调用之后返回的结果是哪壹個 Callable，只知道它是这些 Callable 中壹個执行结束的 Callable 对象。
             如果壹個任务运行完毕或者抛出异常，方法会取消其它的 Callable 的执行。
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public void executeinVokeAny() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Set<Callable<String>> callables = new HashSet<Callable<String>>();
		callables.add(new Callable<String>() {
			public String call() throws Exception {
				return "Task 1";
			}

		});
		callables.add(new Callable<String>() {
			public String call() throws Exception {
				return "Task 2";
			}

		});
		callables.add(new Callable<String>() {
			public String call() throws Exception {
				return "Task 3";
			}

		});
		String invokeAny = executorService.invokeAny(callables);
		System.out.println(invokeAny);
		executorService.shutdown();
	}
	
	/**
	 * 方法 invokeAll() 会调用存在于参数集合中的所有 Callable 对象，并且返回壹個包含 Future 对象的集合，你可以通过这個返回的集合来管理每個 Callable 的执行结果。
             需要注意的是，任务有可能因为异常而导致运行结束，所以它可能并不是真的成功运行了。
             但是我们没有办法通过 Future 对象来了解到这個差异。
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public void executeinVokeAll() throws InterruptedException, ExecutionException{
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Set<Callable<String>> callables = new HashSet<Callable<String>>();
		callables.add(new Callable<String>() {
			public String call() throws Exception {
				return "Task 1";
			}

		});
		callables.add(new Callable<String>() {
			public String call() throws Exception {
				return "Task 2";
			}

		});
		callables.add(new Callable<String>() {
			public String call() throws Exception {
				return "Task 3";
			}

		});
		List<Future<String>> invokeAll = executorService.invokeAll(callables);
		for (Future<String> future : invokeAll) {
			 System.out.println("future.get = " + future.get());
		}
	
		
		
	}
	
	
	
}
