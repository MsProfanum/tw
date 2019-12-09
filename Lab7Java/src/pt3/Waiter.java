package pt3;

public class Waiter {
    private int semaphore;
    private int max;

    public Waiter(int max){
        this.max = max;
        this.semaphore = 0;
    }

    public synchronized void v(){
        try{
            while (semaphore == max){
                wait();
            }
            semaphore++;
            //System.out.println("waiter allowed philosopher to take the fork");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void p(){
        if(semaphore > 0){
            notify();
            //System.out.println("semaphor has : " + semaphore + " philosophers");
        }
        semaphore--;
    }
}
