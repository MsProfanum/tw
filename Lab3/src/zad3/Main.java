package zad3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static int N = 12;

    public static void main(String[] args) throws InterruptedException {
        List<Pair> personList = new ArrayList<>();
        WaiterMonitor waiterMonitor = new WaiterMonitor(N);
        for(int i=0; i<N; i++){
            personList.add(new Pair(waiterMonitor, i));
        }

        for(int i=0; i<N; i++){
            new Thread(personList.get(i).getPerson1()).start();
            new Thread(personList.get(i).getPerson2()).start();
        }for(int i=0; i<N; i++){
            new Thread(personList.get(i).getPerson1()).join();
            new Thread(personList.get(i).getPerson2()).join();
        }
    }
}
