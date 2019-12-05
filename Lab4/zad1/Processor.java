package zad1;

import java.util.Optional;
import java.util.Random;

public class Processor implements Runnable {
    private Buffer buffer;
    private int id;

    public Processor(Buffer buffer, int id){
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
            for(int i=0; i<buffer.getN(); i++){
                Optional<Integer> postProcessValue = processRes(i);

                if(postProcessValue.isPresent()){
                    System.out.println("Process with id " + this.id + " took element " + i +
                             " and gave it back with value " + postProcessValue.get());
                }
            }
    }

    private Optional<Integer> processRes(int resource) {
        try {
            int resourceVal = buffer.get(resource, id);
            buffer.set(resource, resourceVal + 1);
            Thread.sleep(Math.abs(new Random().nextInt()) % 100);
            if(this.id == 2 && resource == 1){
                return Optional.empty();
            }
            return Optional.of(resourceVal + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            buffer.release(resource, id);
        }
    }
}
