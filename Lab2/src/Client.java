public class Client implements Runnable {
    private Semaphore semaphore;
    private Shop shop;
    public Client(Semaphore semaphore,Shop shop){
        this.semaphore = semaphore;
        this.shop = shop;
    }

    public void run(){
        try{
            Basket basket = shop.getBasket();
            System.out.println("Client number " + Thread.currentThread().getName() + " has entered the shop");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Client number" + Thread.currentThread().getName() + " has left the shop");
            shop.returnBasket(basket);
        }catch(Exception err){
            err.printStackTrace();
        }

    }
}
