package pt3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class Philosopher implements Runnable {
    private Fork left, right;
    private Waiter waiter;
    private int id;

    public Philosopher(Waiter waiter, int id, Fork left, Fork right){
        this.id = id;
        this.left = left;
        this.right = right;
        this.waiter = waiter;
    }

    @Override
    public void run() {
        try{
            BufferedWriter fw = new BufferedWriter(new FileWriter("pt3data.csv",true));
            long start = System.currentTimeMillis();

            for(int i=0; i<50; i++){
                System.out.println("Philosopher: " + this.id + " is thinking");
                fw.append(this.id + ",");
                waiter.v();
                left.vFork();
//                Thread.sleep(new Random().nextInt(250)+1);
                System.out.println("Philosopher: " + this.id + " took fork with id: " + this.left.id + " which is left fork for him");
                right.vFork();
//                Thread.sleep(new Random().nextInt(250)+1);
                System.out.println("Philosopher: " + this.id + " took fork with id: " + this.right.id + " which is right fork for him");
                System.out.println("Philosopher: " + this.id + " is eating");
//                Thread.sleep(new Random().nextInt(400)+1);
                left.pFork();
                right.pFork();
                waiter.p();
                fw.append((System.currentTimeMillis()-start) + "\n");
            }
            fw.close();
        }catch (Exception e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
