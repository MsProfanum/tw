package zad1;

import java.util.Optional;
import java.util.Random;

public class Producent implements Runnable {
    private Buffer buffer;
    private int id;

    public Producent(Buffer buffer, int id){
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        for(int i=0; i<buffer.getN(); i++){
                Optional<Integer> postProduce = produceResource(i);

            if(postProduce.isPresent()){
                System.out.println("Process with id " + this.id + " created element " + i);
            }
        }
    }

    private Optional<Integer> produceResource(int resource) {
        try {
            buffer.get(resource, id);
            buffer.set(resource, 0);
            Thread.sleep(Math.abs(new Random().nextInt()) % 100);
            return Optional.of(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            buffer.release(resource, id);
        }
    }
}
