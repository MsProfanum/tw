package Zad1;

public class Main {
    public static void main(String [] args) throws InterruptedException{
        BoundedBuffer buffer = new BoundedBuffer();
        Consumer consumer = new Consumer(buffer);
        Producer producer = new Producer(buffer);
        Thread t1 = new Thread(consumer);
        Thread t2 = new Thread(producer);
        long startTime = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }
}
