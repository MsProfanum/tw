import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

public class Mandelbrot extends JFrame {
    private final int MAX_ITER = 570;
    private BufferedImage I;

    public Mandelbrot(String typeOfExecutor) {
        super("Mandelbrot Set");
        setBounds(100,100,800,600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        ExecutorService executorService;
        Set<Future<Map<Pair<Integer,Integer>, Integer>>> futureSet = new HashSet<>();

        switch (typeOfExecutor){
            case "singleThread":{
                executorService = Executors.newSingleThreadExecutor();
                Callable<Map<Pair<Integer,Integer>, Integer>> callable = new MandelbrotOperation(0,0,getWidth(),getHeight(), MAX_ITER);
                Future<Map<Pair<Integer,Integer>, Integer>> future = executorService.submit(callable);
                futureSet.add(future);
                break;}
            case "fourThreads":{
                executorService = Executors.newFixedThreadPool(4);
                    Callable<Map<Pair<Integer,Integer>, Integer>> callable = new MandelbrotOperation(0, 0, getWidth(), getHeight(),MAX_ITER);
                    Future<Map<Pair<Integer,Integer>, Integer>> future = executorService.submit(callable);
                    futureSet.add(future);

                break;}
            case "eightThreads":{
                executorService = Executors.newFixedThreadPool(8);
                    Callable<Map<Pair<Integer,Integer>, Integer>> callable = new MandelbrotOperation(0, 0, getWidth(),getHeight(),MAX_ITER);
                    Future<Map<Pair<Integer,Integer>, Integer>> future = executorService.submit(callable);
                    futureSet.add(future);


                break;}
            case "tenTimesMoreOp":{
                executorService = Executors.newSingleThreadExecutor();
                int h = getHeight()/10;
                for(int i=0; i<10;i++){
                    Callable<Map<Pair<Integer,Integer>,Integer>> callable = new MandelbrotOperation(0,i*h,getWidth(), (i+1)*h, MAX_ITER);
                    Future<Map<Pair<Integer,Integer>,Integer>> future = executorService.submit(callable);
                    futureSet.add(future);
                }
                break;}
            case "eachOpIsPixel":
                executorService = Executors.newSingleThreadExecutor();
                for(int i=0; i<getWidth();i++){
                    for(int j=0; j<getHeight(); j++){
                        Callable<Map<Pair<Integer,Integer>,Integer>> callable = new MandelbrotOperation(i,j,i+1, j+1, MAX_ITER);
                        Future<Map<Pair<Integer,Integer>,Integer>> future = executorService.submit(callable);
                        futureSet.add(future);
                    }
                }
            default:
                break;
        }

        for(Future<Map<Pair<Integer,Integer>, Integer>> ftr : futureSet){
            Map<Pair<Integer, Integer>, Integer> map = null;
            try {
                map = ftr.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            for(Map.Entry<Pair<Integer, Integer>, Integer> entry : map.entrySet()){
                int x = entry.getKey().getKey();
                int y = entry.getKey().getValue();
                int iter = entry.getValue();
                I.setRGB(x, y, iter | iter << 8);
            }
        }

    }

    @Override
    public void paint(Graphics g){
        g.drawImage(I, 0, 0, this);
    }

    public static Pair<Long, Long> runMandelbrot(String type){
        long time = 0;
        long start, stop;
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        for(int i=0; i<10; i++){
            start = System.currentTimeMillis();
            new Mandelbrot(type).setVisible(true);
            stop = System.currentTimeMillis();
            time += (stop - start);
            min = Math.min(min, time);
            max = Math.max(max, time);
        }

        return new Pair<Long, Long>(min, max-min);
    }

    public static void main(String []args) throws ExecutionException, InterruptedException {
        Pair<Long,Long> time;
        time = runMandelbrot("singleThread");
        System.out.println("Time for single thread: " + time.getKey() + " and departure equals: " + time.getValue());

        time = runMandelbrot("fourThreads");
        System.out.println("Time for four threads: " + time.getKey() + " and departure equals: " + time.getValue());

        time = runMandelbrot("eightThreads");
        System.out.println("Tinme for eight threads: " + time.getKey() + " and departure equals: " + time.getValue());

        time = runMandelbrot("singleThread");
        System.out.println("Time for equal number of threads and operations: " + time.getKey() + " and departure equals: " + time.getValue());

        time = runMandelbrot("tenTimesMoreOp");
        System.out.println("Time for 10xoperations to threads: " + time.getKey() + " and departure equals: " + time.getValue());

        time = runMandelbrot("eachOpIsPixel");
        System.out.println("Time for when one pixel is one operation: " + time.getKey() + " and departure equals: " + time.getValue());

        System.exit(0);

    }
}
