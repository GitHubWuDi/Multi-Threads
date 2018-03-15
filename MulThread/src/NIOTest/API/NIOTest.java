package NIOTest.API;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {

	public static void main(String[] args) throws IOException {
		//scannerOrGather();
		transferForm();
	}
	
	
	public static void channelTest() throws IOException{
		RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\每周工作安排\\study\\NIO\\NIO.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(148);
		int capacity = buf.capacity();
		System.out.println("capacity:"+capacity);
		//channel向buffer当中写入数据
		int bytesRead = inChannel.read(buf);
		//通过put向Buffer当中放入对应的byte数组
		String test = " keke is my wife";
		byte[] bytes = test.getBytes();
		buf.put(bytes);
		
		//buf.rewind();
		System.out.println("writePosition:"+buf.position());
		System.out.println("writelimit:"+buf.limit());
		if (bytesRead != -1) {
			System.out.println("Read " + bytesRead);
			buf.flip();
			System.out.println("readPosition:"+buf.position());
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
			System.out.println("\nreadlimit1:"+buf.limit());
			buf.compact();
			System.out.println("\nreadlimit2:"+buf.limit());
			bytesRead = inChannel.read(buf);
		}
		
		
		
		System.out.println("\n检查当前buffer当中是否存在数据");
		

		
		
		
		aFile.close();
	} 
	
	
	
	public static void readBuffer() throws IOException{
		RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\每周工作安排\\study\\NIO\\NIO.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		String test = "keke is my wife1111111111111111";
		byte[] bytes = test.getBytes();
		//将byte[]放入到buf中
		ByteBuffer buf1 = ByteBuffer.allocate(50);
		buf1.put(bytes);
		inChannel.write(buf1);//向inChannel当中写入数据 //TODO 为什么使用write，文件直接变空了
		
		ByteBuffer buf2 = ByteBuffer.allocate(50);
		int read = inChannel.read(buf2);
		while (read != -1) {
			System.out.println("Read " + read);
			buf2.flip();
			while (buf2.hasRemaining()) {
				System.out.print((char) buf2.get());
			}
			buf2.clear();
			read = inChannel.read(buf2);
		}
	}
	
	
	
	public static void scannerOrGather() throws IOException{
		RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\每周工作安排\\study\\NIO\\NIO.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf1 = ByteBuffer.allocate(50);
		ByteBuffer buf2 = ByteBuffer.allocate(100);
		ByteBuffer[] bufferArray = { buf1, buf2 };
		long read = inChannel.read(bufferArray);
		if (read != -1) {
			System.out.println("Read " + read);
			int length = bufferArray.length;
			System.out.println("length:"+length);
		}
	}
	
	
	/**
	 * 数据在通道之间的传输
	 * @throws IOException
	 */
	public static void transferForm() throws IOException{
		RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\每周工作安排\\study\\NIO\\NIO.txt", "rw");
		FileChannel  fromChannel = aFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\每周工作安排\\study\\NIO\\NIO123.txt", "rw");
		FileChannel  toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
		fromChannel.transferTo(position, count, toChannel);
		
		//toChannel.transferFrom(fromChannel, count, position);

		System.out.println("传送成功");

	}
	
	
	
	
	
	

}
