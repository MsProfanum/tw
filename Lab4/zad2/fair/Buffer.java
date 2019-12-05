package zad2.fair;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final ReentrantLock lock;
    private final Condition condition;
    private final Semaphore semPut, semGet;
    private String buffer;
    private int empty;

    public Buffer(int size){
        lock = new ReentrantLock();
        condition = lock.newCondition();
        semPut = new Semaphore(1, true);
        semGet = new Semaphore(1, true);
        buffer = "";
        empty = size;
    }

    String get(int size){
        String result = "";
        try {
            semGet.acquire();
            lock.lock();
            while(size > buffer.length()){
                condition.await();
            }
            result = buffer.substring(0, size);
            buffer = buffer.substring(size);
            empty += size;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            condition.signalAll();
            lock.unlock();
            semGet.release();
            return result;
        }
    }

    void put(String data){
        try {
            semPut.acquire();
            lock.lock();
            while(data.length() > empty){
                condition.await();
            }
            buffer += data;
            empty -= data.length();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            condition.signalAll();
            lock.unlock();
            semPut.release();
        }
    }
}
