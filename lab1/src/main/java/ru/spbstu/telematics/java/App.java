package ru.spbstu.telematics.java;

import java.util.Scanner;

public final class App {

    private static final String INPUT_SIZE_OF_ARRAY = "Input size of array: ";
    private static final String INPUT_ARRAY = "Input array (%d integers which are separated by spaces)";
    private static final String IS_GENERATE = "Do you want to generate array or input" +
            " it by hands (y - generate, other - by hands)";

    private App() {
    }

    private static int[][] fromArrayToMatrix(int[] array) {
        int n = (int) Math.ceil(Math.sqrt(array.length));
        int[][] matrix = new int[n][n];
        int counter = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < n; j++) {
                    if (counter == array.length) {
                        return matrix;
                    }
                    matrix[i][j] = array[counter++];
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    if (counter == array.length) {
                        return matrix;
                    }
                    matrix[i][j] = array[counter++];
                }
            }
        }
        return matrix;
    }

    private static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INPUT_SIZE_OF_ARRAY);
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println(IS_GENERATE);
        boolean isGenerate = scanner.next().equals("y");
        System.out.println(isGenerate);

        if (isGenerate) {
            for (int i = 0; i < size; i++) {
                array[i] = i;
            }
        } else {
            System.out.println(String.format(INPUT_ARRAY, size));
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextInt();
            }
        }
        return array;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = fromArrayToMatrix(inputArray());
        printMatrix(matrix);
    }
}
