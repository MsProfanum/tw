public class IncDecSync {
    public static class SyncCounter{
        private int c = 0;

        public synchronized void inc(){
                c++;
        }

        public synchronized void dec(){
            c--;
        }

        public int value(){
            synchronized (this){
                return c;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException{
        final long startTime, endTime;
        SyncCounter syncCounter = new SyncCounter();
        startTime = System.currentTimeMillis();

        Thread t1 = new Thread(){
            public void run(){
                for(int i=0; i<100000000;i++){
                    syncCounter.inc();
                }
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                for(int i=0; i<100000000;i++){
                    syncCounter.dec();
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        endTime = System.currentTimeMillis();

        System.out.println("Counter equals: " + syncCounter.value());
        System.out.println("Time: " + (endTime-startTime));

    }
}
