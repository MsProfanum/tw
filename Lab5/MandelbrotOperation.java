import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class MandelbrotOperation implements Callable {
    private int x, y, xk, yk, iter;
    private Map<Pair<Integer, Integer>, Integer> hashMap;
    private double zx, zy, cX, cY, tmp;
    private final double ZOOM = 150;

    public MandelbrotOperation(int x, int y, int xk, int yk, int iter) {
        hashMap = new HashMap<>();
        this.x = x;
        this.y = y;
        this.xk = xk;
        this.yk = yk;
        this.iter = iter;
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> call() throws Exception {
        for(int y = this.y; y < this.yk; y++){
            for(int x = this.x; x < this.xk; x++){
                zx = zy = 0;
                cX = (x-400) / ZOOM;
                cY = (y-300) / ZOOM;
                int it = iter;
                while(zx*zx + zy*zy < 4 && it > 0){
                    tmp = zx*zx - zy*zy + cX;
                    zy = 2.0*zx*zy + cY;
                    zx = tmp;
                    it--;
                }
                hashMap.put(new Pair<Integer, Integer>(x, y), it);
            }
        }

        return hashMap;
    }
}
