public class IncDecNotSync {
    public static class Counter {
        private int c = 0;

        public void increment(){
            c++;
        }

        public void decrement(){
            c--;
        }

        public int value(){
            return c;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        final long startTime, endTime;
        Counter counter = new Counter();
        startTime = System.currentTimeMillis();

        Thread t1 = new Thread(){
            public void run(){
                for(int i=0; i<10000; i++){
                    counter.increment();
                }
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                for(int i=0; i<10000; i++){
                    counter.decrement();
                }
            }
        };

        t1.start();
        t2.start();
        t2.join();
        t1.join();

        endTime = System.currentTimeMillis();
        System.out.println("Counter equals: " + counter.value());
        System.out.println("Time spent: " + (endTime-startTime));
    }
}
