public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Consumer consumer = new Consumer(buffer);
        Producer producer = new Producer(buffer);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Time: " + (System.currentTimeMillis() - start));
    }
}
