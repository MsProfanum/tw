package zad2;

import java.util.Random;

public class PrinterRunnable implements Runnable {

    @Override
    public void run() {
        int dr_drukarki;
        while (true){
            try {
                int nr_drukarki = PrinterMonitor.zarezerwuj();
                Thread.sleep(new Random().nextInt(1000)+101);
                System.out.println(Thread.currentThread().getName() + " drukuje... id: " + nr_drukarki);
                PrinterMonitor.zwolnij(nr_drukarki);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
