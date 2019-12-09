package pt3;

import java.util.concurrent.Semaphore;

public class Fork {
    int id;
    boolean flag;

    public Fork(int id){
        this.id = id;
        this.flag = false;
    }

    public synchronized void vFork() throws InterruptedException {
        while(flag){
            wait();
        }
        flag = true;
    }

    public synchronized void pFork(){
        if(flag){
            notify();
        }
        flag=false;
    }
}
