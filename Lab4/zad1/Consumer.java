package zad1;

import java.util.Optional;
import java.util.Random;

public class Consumer implements Runnable {
    private Buffer buffer;
    private int id;

    public Consumer(Buffer buffer, int id){
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
            for(int i=0; i<buffer.getN(); i++){
                Optional<Integer> consumedVal = consumeRes(i);

                if(consumedVal.isPresent()){
                    System.out.println("Consumer with id " + this.id + " took element " + i +
                             " and value " + consumedVal.get());
                }
            }
    }

    private Optional<Integer> consumeRes(int resource) {
        try {
            int resourceVal = buffer.get(resource, id);
            Thread.sleep(Math.abs(new Random().nextInt()) % 100);
            return Optional.of(resourceVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            buffer.release(resource, id);
        }
    }
}
