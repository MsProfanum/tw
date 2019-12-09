package pt1;

import java.io.*;
import java.util.Random;

public class Philosopher implements Runnable {
    private Fork left, right;
    private int id;

    public Philosopher(int id, Fork left, Fork right){
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        try{
            BufferedWriter fw = new BufferedWriter(new FileWriter("pt1data.csv", true));
            long start = System.currentTimeMillis();

            for(int i=0;i<10;i++){
                System.out.println("Philosopher: " + this.id + " is thinking");
                fw.append(this.id + ",");
                synchronized (this.right){
                    //Thread.sleep(new Random().nextInt(1000)+1);
                    System.out.println("Philosopher: " + this.id + " took fork with id: " + this.right.id + " which is right fork for him");
                    synchronized (this.left){
                        //Thread.sleep(new Random().nextInt(1000)+1);
                        System.out.println("Philosopher: " + this.id + " took fork with id: " + this.left.id + " which is left fork for him");
                        System.out.println("Philosopher: " + this.id + " is eating");
                        //Thread.sleep(new Random().nextInt(400)+1);
                    }
                }
                fw.append((System.currentTimeMillis()-start) + "\n");
            }
            fw.close();
        }catch (Exception e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
