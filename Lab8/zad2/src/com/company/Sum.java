package com.company;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Sum extends RecursiveTask<BigInteger> {
    private int start, stop, seq;
    private List<BigInteger> arr;

    public Sum(int start, int seq, int stop, List<BigInteger> arr){
        this.start = start;
        this.seq = seq;
        this.stop = stop;
        this.arr = arr;
    }

    @Override
    protected BigInteger compute() {
        BigInteger sum = new BigInteger("0");
        if(seq > (stop-start))
            for(int i=start; i<stop; i++)
                sum = sum.add(arr.get(i));
        
        else{
            Sum task1 = new Sum(start, seq, (stop-start)/2, arr);
            Sum task2 = new Sum((stop-start)/2, seq, stop, arr);

            task1.fork();
            task2.fork();

            sum = sum.add(task1.join().add(task2.join()));
        }
        return sum;
    }
}
