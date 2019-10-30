package zad2;


import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int M = 7;
    private static final int N = 23;

    public static void main(String [] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        List<Printer> drukarki = new ArrayList<>();

        for(int i=0; i<M; i++) {
            drukarki.add(new Printer(i));
        }

        for(int i = 0; i<N; i++){
            threads.add(new Thread(new PrinterRunnable()));
        }

        for(int i=0; i<N; i++){
            threads.get(i).start();
        }for(int i=0; i<N; i++){
            threads.get(i).join();
        }
    }
}
