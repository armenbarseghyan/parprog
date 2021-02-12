package ru.spbstu.telematics.java;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static ru.spbstu.telematics.java.App.fromArrayToMatrix;

public class SnakeTest {

    @Test
    public void emptyArrayTest() {
        int[] emptyArray = new int[0];
        int[][] expectedMatrix = new int[0][0];
        int[][] matrix = fromArrayToMatrix(emptyArray);
        Assertions.assertArrayEquals(matrix, expectedMatrix);
    }

    @Test
    public void arrayHasSizeWithoutIntSquareRootTest() {
        int[] arr = {1, 2, 3};
        int[][] expectedMatrix = {
                {1, 2},
                {0, 3}
        };
        int[][] matrix = fromArrayToMatrix(arr);
        Assertions.assertArrayEquals(matrix, expectedMatrix);
    }
    @Test
    public void arrayHasSizeWithIntSquareRootTest() {
        int[] arr = {1, 2, 3, 4};
        int[][] expectedMatrix = {
                {1, 2},
                {4, 3}
        };
        int[][] matrix = fromArrayToMatrix(arr);
        Assertions.assertArrayEquals(matrix, expectedMatrix);
    }


}

