public class BinarySemaphore {
    private boolean state;

    public BinarySemaphore(){
        this.state = true;
    }

    public synchronized void acquire(){ //prosi i czeka, po dostaniu zmniejsza i blokuje
        while(!state){
            try {
                wait();
            }catch (InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }
        state = false;
    }

    public synchronized void release(){ //oddaje i powiadamia
        if(!state){
            notifyAll();
        }
        state = true;
    }
}
