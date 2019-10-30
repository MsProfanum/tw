package zad3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaiterMonitor {
    private ReentrantLock lock = new ReentrantLock();
    private Condition isFull = lock.newCondition();
    private List<Condition> queue = new ArrayList<>();
    private int eating = 0;

    public WaiterMonitor(int N){
        for(int i=0; i<N; i++){
            queue.add(lock.newCondition());
        }
    }

    public void put(int id) {
        lock.lock();
        try{
            if(lock.getWaitQueueLength(queue.get(id)) == 0){
                queue.get(id).await();
            } else if(eating != 0){
                isFull.await();
            }else{
                eating = 2;
                queue.get(id).signal();
            }
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        lock.unlock();
    }

    public void take() {
        lock.lock();
        if(--eating == 0){
            isFull.signal();
        }
        lock.unlock();
    }

}
