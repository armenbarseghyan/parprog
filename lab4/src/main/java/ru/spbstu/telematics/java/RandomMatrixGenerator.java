package ru.spbstu.telematics.java;

import java.util.Random;

public class RandomMatrixGenerator {

    double[][] get(int size){
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                matrix[i][j] = new Random().nextDouble()*100;
                matrix[j][i]=matrix[i][j];
            }
        }

        return matrix;
    }
}
