public class Buffer {
    private String buf = "";
    boolean empty = true;

    public synchronized void put(String buf){
        while(!empty){
            try {
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        empty = false;
        this.buf = buf;
        //System.out.println(buf);
        notifyAll();
    }

    public synchronized String take(){
        while(empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = true;
        notifyAll();
        return buf;
    }
}
