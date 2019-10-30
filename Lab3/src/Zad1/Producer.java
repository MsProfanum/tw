package Zad1;

public class Producer implements Runnable{
    private static final int ILOSC=1000;
    private BoundedBuffer buffer;

    public Producer(BoundedBuffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i=0; i<ILOSC; i++){
            try {
                buffer.put("message" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
