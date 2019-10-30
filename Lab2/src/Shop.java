import java.util.ArrayList;
import java.util.List;

public class Shop {
    private static final int NUM_OF_BASKETS = 5;
    private static final int NUM_OF_CLIENTS = 10;
    Semaphore semaphore;
    List<Basket> baskets;

    public Shop(Semaphore semaphore, List<Basket> baskets){
        this.semaphore = semaphore;
        this.baskets = baskets;
    }

    public Basket getBasket() throws Exception{
        semaphore.acquire();
        synchronized (baskets) {
            for (Basket basket : baskets) {
                if (basket.isFree()) {
                    return basket;
                }
            }
        }
        throw new Exception("sth wrong");
    }

    public void returnBasket(Basket basket){
        semaphore.release();
        basket.setStatus(true);
    }

    public static void main(String [] args) throws InterruptedException {
        List<Basket> baskets = new ArrayList<>();
        List<Thread> clients = new ArrayList<>();
        for(int i=0; i<NUM_OF_BASKETS; i++){
            baskets.add(new Basket(i+1));
        }
        Shop shop = new Shop(new Semaphore(NUM_OF_CLIENTS), baskets);
        for(int i=0; i<NUM_OF_CLIENTS; i++){
            clients.add(new Thread(new Client(shop.semaphore, shop)));
        }

        long start = System.currentTimeMillis();

        for(int i=0; i<NUM_OF_CLIENTS; i++){
            clients.get(i).start();
        }
        for(int i=0; i<NUM_OF_CLIENTS; i++){
            clients.get(i).join();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time equals: " + (end-start));
    }
}
