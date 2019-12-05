package zad2.fair;

import java.util.Random;

public class Producent implements Runnable {
    private Buffer buffer;
    private int maxLength;

    public Producent(Buffer buffer, int maxLength){
        this.buffer = buffer;
        this.maxLength = maxLength;
    }


    @Override
    public void run() {
        for(int i=0; i<10; i++){
            int size = new Random().nextInt(maxLength - 1) + 1;
            StringBuilder stringBuilder = new StringBuilder();
            for(int j=0; j<size; j++){
                stringBuilder.append((char) (new Random().nextInt(26)+97));
            }
            buffer.put(stringBuilder.toString());
            System.out.println("Producent put " + size + " bytes, data is: " + stringBuilder.toString());
        }
    }
}
