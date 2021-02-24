package ru.spbstu.telematics.java;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;


public class GradientDescent {
    private final double EPS = 1e-1;
    private int nThreads;




    public GradientDescent(int count){
        nThreads = count;
    }



    public void minimize(FunctionInterface f,
                            Vector startVector,
                            double precision,
                            double stepSize,
                          Vector res) {
        try {
            Vector nextVector = new Vector(startVector.getSize());

            while (true){

                step(nextVector, f, startVector,stepSize);

                Boolean stopCriteria1 = getNorm(Vector.sub(nextVector, startVector, nThreads)) < precision;
                Boolean stopCriteria2 = false;
                for (double x:nextVector.getX())
                    if (x<0||x>10){
                    stopCriteria2 = true;
                    break;
                    }

                Boolean stopCriteria3 = Math.abs(f.f(nextVector) - f.f(startVector)) < precision;

                 if (stopCriteria1 || stopCriteria2 ||stopCriteria3) {
                    res.copy(nextVector);
                    break;
                }


                startVector.copy(nextVector);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        res = null;

    }





    private Vector getGradient(FunctionInterface f, Vector Vector) throws InterruptedException {

        double []coordinates = new double[Vector.getSize()];
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        CountDownLatch countDownLatch = new CountDownLatch(Vector.getSize());
        for (int i =0; i< Vector.getSize(); i++){
            final int finalI = i;
            executorService.submit(() -> {
                Vector dVector = new Vector(Vector);
                dVector.getX()[finalI]+=EPS;
                coordinates[finalI]  = (f.f(dVector) - f.f(Vector)) / EPS;
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        return new Vector(coordinates);
    }

    private double getNorm(Vector Vector) {
        double res=0;
        for (double x: Vector.getX())
            res+=x*x;
        return Math.sqrt( res);
    }
    private void step(Vector nextVector,
                      FunctionInterface f,
                      Vector startVector,
                      double stepSize) throws InterruptedException {

        Vector grad = getGradient(f, startVector);
        nextVector.copy( Vector.sub(startVector, Vector.mul(grad, stepSize, nThreads), nThreads));
    }
}