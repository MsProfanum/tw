public class Semaphore {
    private int baskets;

    public Semaphore(int baskets){
        this.baskets = baskets;
    }

    public int getBaskets() {
        return baskets;
    }

    public synchronized void acquire(){
        while(baskets <= 0){
            try{
                this.wait();
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        baskets--;
    }

    public synchronized void release(){
        baskets++;
        this.notifyAll();
    }
}
