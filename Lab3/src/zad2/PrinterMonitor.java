package zad2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterMonitor {
    private static List<Lock> locks = initLocks();


    public static List<Lock> initLocks(){
        List<Lock> locks = new ArrayList<>();
        for(int i=0; i<7; i++){
            locks.add(new ReentrantLock());
        }

        return locks;
    }

    public static int zarezerwuj(){
        int i = 0;
        while(!locks.get(i).tryLock()){
            i = (i+1)%7;
        }

        return i;
    }

    public static void zwolnij(int nr_drukarki){
        locks.get(nr_drukarki).unlock();
    }
}
