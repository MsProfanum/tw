package zad2.fair;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int M = 10;
    private static final int PRODUCENTS = 2;
    private static final int CONSUMERS = 2;

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        Buffer buffer = new Buffer(M*2);

        for(int i=0; i < PRODUCENTS; i++){
            Producent producent = new Producent(buffer, M);
            threads.add(new Thread(producent));
        }

        for(int i=0; i < CONSUMERS; i++){
            Consumer consumer = new Consumer(buffer, M);
            threads.add(new Thread(consumer));
        }

        for(int i=0; i<threads.size(); i++){
            threads.get(i).start();
        }
    }
}
