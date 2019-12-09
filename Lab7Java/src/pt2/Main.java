package pt2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        List<Philosopher> philosophers = new ArrayList<>();
        List<Fork> forks = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for(int i=0; i<5; i++){
            forks.add(new Fork(i+1));
        }

        for(int i=0; i<5; i++){
//            if(i==4){
//                philosophers.add(new Philosopher(i+1, forks.get((i+1)%5), forks.get(i)));
//            }else{
                philosophers.add(new Philosopher(i+1, forks.get(i), forks.get((i+1)%5)));
//            }
            threads.add(new Thread(philosophers.get(i)));
        }

        for(int i=0; i<5; i++){
            threads.get(i).start();
        }
    }
}
