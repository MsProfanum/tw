import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ClassicApproach {

    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer(2);

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    buffer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    buffer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

    }

    static class Buffer{
        private int size;
        private Queue<Integer> list;

        public Buffer(int size){
            this.size = size;
            list = new LinkedList<>();
        }

        public void produce() throws InterruptedException {
            int val;

            while(true){
                synchronized (this){
                    while(list.size() >= size){
                        wait();
                    }

                    val = new Random().nextInt(10)+1;
                    list.add(val);

                    System.out.println("Number produced: " + val);

                    notify();

                    Thread.sleep(300);
                }
            }
        }

        public void consume() throws InterruptedException {
            while (true){
                synchronized (this){
                    while(list.size() == 0){
                        wait();
                    }

                    int val = list.poll();
                    System.out.println("Number consumed: " + val);

                    notify();

                    Thread.sleep(300);
                }
            }
        }
    }
}
