package zad3;

import java.util.Random;

public class Person implements Runnable{
    private int id;
    private WaiterMonitor waiterMonitor;

    public Person(WaiterMonitor waiterMonitor,int id){
        this.id = id;
        this.waiterMonitor = waiterMonitor;
    }

    @Override
    public void run() {
        while(true){
            try{
                waiterMonitor.put(id);
                Thread.sleep(new Random().nextInt(1000)+101);
                System.out.println(Thread.currentThread().getName() + " sat at the table");
                waiterMonitor.take();
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}
