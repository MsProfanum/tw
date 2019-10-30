public class IncDec {
    public static class SyncCounter{
        private int c;
        private BinarySemaphore semaphore;

        public SyncCounter(int c, BinarySemaphore semaphore){
            this.semaphore = semaphore;
            this.c = c;
        }

        public void inc(){
            c++;
        }

        public void dec(){
            c--;
        }

        public int value(){
            return c;
        }
    }
    public static void main(String[] args) throws InterruptedException{
        final long startTime, endTime;
        BinarySemaphore semaphore = new BinarySemaphore();
        SyncCounter syncCounter = new SyncCounter(0, semaphore);
        startTime = System.currentTimeMillis();

        Thread t1 = new Thread(){
            public void run(){
                for(int i=0; i<1000000;i++){
                    semaphore.acquire();
                    syncCounter.inc();
                    semaphore.release();
                }
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                for(int i=0; i<1000000;i++){
                    semaphore.acquire();
                    syncCounter.dec();
                    semaphore.release();
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
