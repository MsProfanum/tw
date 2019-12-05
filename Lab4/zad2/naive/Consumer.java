package zad2.naive;

import java.util.Random;

public class Consumer implements Runnable {
    private int id;
    private int M;
    private Buffer buffer;

    public Consumer(int M, Buffer buffer){
        this.M = M;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){
            String data = "";
            try {
                data = buffer.get(new Random().nextInt(M-1)+1);
                //System.out.println("Consumer took " + data.length() + " bytes, data is: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
