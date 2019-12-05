package zad2.naive;

import java.util.Random;

public class Producent implements Runnable {
    private int id;
    private int M;
    private Buffer buffer;
    private int maxLength;

    public Producent(int M, Buffer buffer){
        this.M = M;
        this.buffer = buffer;
        this.maxLength = M;
    }

    @Override
    public void run(){
                for(int i=0;i<10;i++){
                    try{
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j=0; j<(new Random().nextInt(M-1)+1); j++){
                            stringBuilder.append((char)(new Random().nextInt(26)+97));
                        }
                        String str = stringBuilder.toString();
                        buffer.put(str);
                        //System.out.println("Producent put " + str.length() + " bytes, data is: " + str);
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
    }
}
