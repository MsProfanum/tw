package zad1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int BUFFER_SIZE = 10;
    private static final int CONSUMER = 1;
    private static final int PRODUCENT = 1;
    private static final int PROCESSORS = 5;

    public static void main(String[] args){
        Buffer buffer = new Buffer(BUFFER_SIZE);
        List<Thread> threads = new ArrayList<>(PROCESSORS+2);

        for(int i=0; i < PRODUCENT; i++){
            Producent producent = new Producent(buffer, i);
            threads.add(new Thread(producent));
        }

        for(int i=0; i<PROCESSORS; i++){
            Processor processor = new Processor(buffer, i+1);
            threads.add(new Thread(processor));
        }

        for(int i=0; i<CONSUMER; i++){
            Consumer consumer = new Consumer(buffer, PROCESSORS+1);
            threads.add(new Thread(consumer));
        }

        long start = System.currentTimeMillis();
        for(int i=0; i<threads.size(); i++){
            threads.get(i).start();
        }

        for(int i=0; i<threads.size(); i++){
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("TIME: " + (System.currentTimeMillis() - start));
    }
}
