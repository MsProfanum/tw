public class Basket {
    private int id;
    private boolean isFree;

    public Basket(int id) {
        this.id = id;
        this.isFree = true;
    }

    public boolean isFree(){
        return isFree;
    }

    public int getNum(){
        return id;
    }

    public void setStatus(boolean status){
        isFree = status;
    }
}
