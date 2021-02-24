package ru.spbstu.telematics.java;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;


public class App 
{
    public static void main(String[] args) throws Exception {
       RandomMatrixGenerator randomMatrixGenerator = new RandomMatrixGenerator();

       int maxSize=1450;
       int maxThreads = 8;
        for (int i=10; i<= maxSize; i+=503
        ) {
            System.out.println("Длина вектора " + i);
            double[] coordinates = new double[i];

            for (int k=0; k<i; k++)
                coordinates[k] = new Random().nextDouble()*10;
            for (int j = 1; j <= maxThreads; j++) {
                System.out.println("\tКоличество потоков:  " + j);
                FunctionInterface function = new Function(randomMatrixGenerator.get(i));
                GradientDescent descentMinimizer = new GradientDescent(j);
                Vector startVector = new Vector(coordinates);
                Instant start = Instant.now();
                Vector res = new Vector(i);
                descentMinimizer.minimize(
                        function,
                        startVector,
                        1e-2,
                        1e-2,
                        res);
                Instant finish = Instant.now();
                System.out.printf("Working time = %d milliseconds%n", Duration.between(start, finish).toMillis());
            }
        }

    }
}
