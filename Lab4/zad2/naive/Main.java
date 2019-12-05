package zad2.naive;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int M = 100000;
    private static final int PRODUCENTS = 10;
    private static final int CONSUMERS = 10;

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();
        Buffer buffer = new Buffer(M *2);

        for(int i=0; i<PRODUCENTS; i++){
            Producent producent = new Producent(M, buffer);
            threads.add(new Thread(producent));
        }

        for(int i=0; i<CONSUMERS; i++){
            Consumer consumer = new Consumer(M, buffer);
            threads.add(new Thread(consumer));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

    }
}
