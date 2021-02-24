package ru.spbstu.telematics.java;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Vector {
    private double []values;
    public Vector (int size){
        values = new double[size];
        for (double x: values)
            x=0;
    }


    public Vector(double []x) {
        values = new double[x.length];
        System.arraycopy(x,0,values, 0, x.length);
    }

    public Vector(Vector p){
        values = new double[p.getSize()];
        System.arraycopy(p.values,0,this.values, 0, p.getSize());
    }

    public void copy(Vector p){
        System.arraycopy(p.values,0,this.values, 0, p.getSize());
    }
    public int getSize(){
        return values.length;
    }

    public double[] getX() {
        return values;
    }



    public static Vector sub(Vector a, Vector b, int nThreads) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        CountDownLatch countDownLatch = new CountDownLatch(a.getSize());
        Vector res = new Vector(a.values.length);
        for (int i =0; i< a.getSize(); i++){
            final int finalI = i;
            executorService.submit(() -> {
                res.values[finalI] = a.values[finalI] - b.values[finalI];
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        return res;
    }

    public static Vector mul(Vector a, double s, int nThreads) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        CountDownLatch countDownLatch = new CountDownLatch(a.getSize());

        Vector res = new Vector(a.values.length);
        for (int i = 0; i < a.values.length; i++) {
            final int finalI = i;
            executorService.submit(() -> {
                res.values[finalI] = a.values[finalI] * s;
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        return res;
    }


    @Override
    public String toString() {
        String res = new String();
        for (int i = 0; i < values.length; i++)
            res+=String.format("x[%d] = %.2f\n", i, values[i]);
        return res;
    }



}