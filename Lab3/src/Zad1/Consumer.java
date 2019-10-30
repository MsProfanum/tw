package Zad1;

public class Consumer implements Runnable {
    private static final int ILOSC= 1000;
    private BoundedBuffer buffer;

    public Consumer(BoundedBuffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i=0; i<ILOSC; i++){
            try {
                String message = buffer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
