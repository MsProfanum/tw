package zad1;

import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private int N;
    private List<Cell> cells;

    public Buffer(int size){
        this.N = size;
        cells = new ArrayList<>(N);
        for(int i=0; i < N; i++){
            cells.add(new Cell());
        }
    }

    public int getN() {
        return N;
    }

    int get(int id, int i) {
        return cells.get(id).get(i);
    }

    void set(int id, int NewVal){
        cells.get(id).set(NewVal);
    }

    void release(int id, int i) {
        cells.get(id).release(i);
    }
}
