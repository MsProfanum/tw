package zad2.fair;

import java.util.Random;

public class Consumer implements Runnable {
    private final Buffer buffer;
    private final int maxLen;

    public Consumer(Buffer buffer, int maxLen){
        this.buffer = buffer;
        this.maxLen = maxLen;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            int size = new Random().nextInt(maxLen-1)+1;
            String data = buffer.get(size);
            System.out.println("Consumer took " + size + " bytes and data is: " + data);
        }
    }
}
