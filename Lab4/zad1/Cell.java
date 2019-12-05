package zad1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {
    private int id;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int value;

    public Cell(){
        id = -1;
        value = 0;
    }

    public int get(int id){
        lock.lock();
        try{
            while(this.id+1 != id){
                condition.await();
            }
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }finally {
            return value;
        }
    }

    void set(int val){
        value = val;
    }

    public void release(int id){
        try{
            this.id = id;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
