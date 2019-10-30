public class Producer implements Runnable {
    private static final int ILOSC = 1000;
    private Buffer buffer;

    public Producer(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i=0; i<ILOSC; i++){
            buffer.put("message" + i);
        }
    }
}
