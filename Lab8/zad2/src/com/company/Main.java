package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        List<BigInteger> arr = new ArrayList<>();
        ForkJoinPool fjp = new ForkJoinPool();

        for(int i=0; i<10000000; i++){
            arr.add(new BigInteger(String.valueOf(new Random().nextInt())));
        }

        long start1 = System.currentTimeMillis();
        BigInteger sum1 = new BigInteger("0");
        for (int i=0; i<10000000; i++){
            sum1 = sum1.add(arr.get(i));
        }
        System.out.println("Sum1 of " + sum1 + " in time " + (System.currentTimeMillis() - start1));


        Sum task = new Sum(0, arr.size(), 10000, arr);

        long start2 = System.currentTimeMillis();

        BigInteger sum2 = fjp.invoke(task);

        System.out.println("Sum2 of " + sum2 + " in time " + (System.currentTimeMillis() - start2));
    }
}
