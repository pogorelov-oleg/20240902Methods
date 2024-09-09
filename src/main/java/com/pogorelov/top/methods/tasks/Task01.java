package com.pogorelov.top.methods.tasks;

/**
 * 1. Написать и протестировать методы работы с квадратными матрицами (матрицы представить в виде двухмерных
 * массивов).
 * Должны присутствовать методы:
 * ■ создания единичной (диагональной) матрицы;
 * ■ создания нулевой матрицы;
 * ■ сложение матриц;
 * ■ умножения матриц;
 * ■ умножение матрицы на скаляр;
 * ■ определение детерминанта матрицы;
 * ■ вывод матрицы на консоль.
 */
public class Task01 {
    /**
     * Метод создает квадратную единичную (диагональную) матрицу.
     */
    public static int[][] createDiagonalMatrix(int length, int value) {
        int[][] array = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (j == i) array[i][j] = value;
                else array[i][j] = 0;
            }
        }
        return array;
    }

    /**
     * Метод создает квадратную нулевую матрицу.
     */
    public static int[][] createNullMatrix(int length) {
        int[][] array = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                array[i][j] = 0;
            }
        }
        return array;
    }

    /**
     * Метод возвращает сумму двух матриц.
     */
    public static int[][] additionMatrix(int[][] matrixA, int[][] matrixB) {
        int[][] result = new int[matrixA.length][matrixA[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return result;
    }

    /**
     * Метод возвращает произведение двух матриц.
     */
    public static int[][] multiplyOfTwoMatrix(int[][] matrixA, int[][] matrixB) {
        if (matrixA[0].length != matrixB.length) {
            System.out.println("Эти матрицы нельзя перемножить");
            return null;
        }
        int[][] result = new int[matrixA.length][matrixB[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = 0;
                for (int k = 0; k < matrixA[0].length; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Метод возвращает произведение матрицы на скаляр.
     */
    public static int[][] multiplyMatrixByNumber(int[][] matrix, int number) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[i][j] * number;
            }
        }
        return result;
    }

    /**
     * Рекурсивный метод возвращает детерминант(определитель) матрицы.
     */
    public static int detMatrix(int[][] matrix) {
        int result = 0;
        if (matrix.length == 2) {
            result = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        } else {
            int k;
            for (int i = 0; i < matrix.length; i++) {
                if (i % 2 != 0) {
                    k = -1;
                } else {
                    k = 1;
                }
                result += k * matrix[0][i] * detMatrix(getMinor(matrix, i));
            }
        }
        return result;
    }

    /**
     * Приватный метод, который возвращает нужный нам минор (используется в рекурсивном методе detMatrix)
     */
    private static int[][] getMinor(int[][] matrix, int column) {
        int row = 0;
        int minorLength = matrix.length - 1;
        int[][] minor = new int[minorLength][minorLength];
        int dI = 0;
        int dJ;
        for (int i = 0; i <= minorLength; i++) {
            dJ = 0;
            for (int j = 0; j <= minorLength; j++) {
                if (i == row) {
                    dI = 1;
                } else {
                    if (j == column) {
                        dJ = 1;
                    } else {
                        minor[i - dI][j - dJ] = matrix[i][j];
                    }
                }
            }
        }
        return minor;
    }

    /**
     * Метод выводит матрицу в консоль.
     */
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }
}
