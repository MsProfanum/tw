package zad2.naive;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private int M;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private String data;
    private int empty;

    public Buffer(int M){
        this.M = M;
        data = "";
        this.empty = M;
    }

    long stop, start;

    public void put(String string) throws InterruptedException{
        start = System.currentTimeMillis();
        int size = string.length();
        lock.lock();
        while(size > empty){
            condition.await();
        }
        data += string;
        empty -= string.length();
        condition.signalAll();
        lock.unlock();
        stop = System.currentTimeMillis();
        System.out.println("Put method time: " + (stop-start));
    }

    public String get(int size) throws InterruptedException {
        start = System.currentTimeMillis();
        lock.lock();
        while(size > data.length()){
            condition.await();
        }
        String result = data.substring(0,size);
        data = data.substring(size);
        empty += size;
        condition.signalAll();
        lock.unlock();
        stop = System.currentTimeMillis();
        System.out.println("Get method time: " + (stop-start));

        return result;
    }
}
