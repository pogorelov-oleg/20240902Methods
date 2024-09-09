package com.pogorelov.top.methods.tasks;

/**
 * 2. Написать и протестировать перегруженный метод, выводящий на экран:
 * ■ одномерный массив типа int;
 * ■ одномерный массив типа String;
 * ■ двухмерный массив типа int;
 * ■ двухмерный массив типа float.
 */
public class Task02 {
    /**
     * Метод выводит в консоль одномерный массив типа int.
     */
    public static void printArray(int[] array) {
        for (int element : array) {
            System.out.print(element + "\t");
        }
        System.out.println();
    }

    /**
     * Метод выводит в консоль одномерный массив типа String.
     */
    public static void printArray(String[] array) {
        for (String element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /**
     * Метод выводит в консоль двумерный массив типа int.
     */
    public static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int element : row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Метод выводит в консоль двумерный массив типа float.
     */
    public static void printArray(float[][] array) {
        for (float[] row : array) {
            for (float element : row) {
                System.out.print(element + "\t\t");
            }
            System.out.println();
        }
    }
}
