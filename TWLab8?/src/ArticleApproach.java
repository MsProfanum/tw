/*
* Approach that enables N number of producers and N number of consumers executing task at the same time
* this is to ensure that the resources are fully optimized
* */

import java.util.*;

public class ArticleApproach {

    public static void main(String[] args) throws InterruptedException {
        final int NO = 3;
        Buffer buffer = new Buffer(10);

        List<Producer> producerList = new ArrayList<>();
        List<Consumer> consumerList = new ArrayList<>();

        for(int i=0; i<NO; i++){
            producerList.add(new Producer(buffer));
            consumerList.add(new Consumer(buffer));
        }

        for(int i=0; i<NO; i++){
            producerList.get(i).start();
            consumerList.get(i).start();
        }

        for(int i=0; i<NO; i++){
            producerList.get(i).join();
            consumerList.get(i).join();
        }
    }

    /*
    * This class is inherited from Thread class
    * The run method will start the loop which will produce the items
    * The items will be produced by being built by Random class
    * Then the item will be put in the queue
    * */
    static class Producer extends Thread{
        Buffer buffer;

        public Producer(Buffer buffer){
            this.buffer = buffer;
        }

        public void run(){
            int val;
            while(true){
                val = new Random().nextInt(10)+1;

                try {
                    buffer.enqueue(val);
                    System.out.println("Producer " + this.getName() + " produced value " + val);
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /*
    * This class is inherited from Tread class
    * The run method will start the loop which will continuously check the need for consuming operation and dequeue if need be
    * */
    static class Consumer extends Thread{
        Buffer buffer;

        public Consumer(Buffer buffer){
            this.buffer = buffer;
        }

        public void run(){
            int val;
            while(true) {
                try {
                    val = buffer.dequeue();
                    System.out.println("Consumer " + this.getName() + " consumed value " + val);
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Buffer{
        private int size;
        private Queue<Integer> list;

        public Buffer(int size){
            this.size = size;
            list = new LinkedList<>();
        }

        public synchronized void enqueue(int val) throws InterruptedException {
            while (list.size() >= size){
                wait();
            }

            list.add(val);

            notifyAll();
        }

        public synchronized int dequeue() throws InterruptedException {
            while (list.size() == 0){
                wait();
            }
            int val = list.poll();

            notifyAll();

            return val;
        }

    }
}
