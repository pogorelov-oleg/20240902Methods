package com.pogorelov.top.methods.tasks;

import java.util.Random;

/**
 * 4. На рисунке показан пример треугольника из чисел.
 * ____7
 * ___3_8
 * __8_1_0
 * _2_7_4_4
 * 4_5_2_6_5
 * Написать метод:
 * ■ выводящий значения треугольника на консоль в таком
 * виде как на рисунке;
 * ■ вычисляющий наибольшую сумму чисел, через
 * которые проходит путь, начинающийся на вершине и
 * заканчивающийся где-то на основании.
 * 1. Каждый шаг может идти диагонально вниз-направо
 * или диагонально вниз-налево.
 * 2. Количество строк в треугольнике >1, но <100.
 * 3. Числа в треугольнике все целые от 0 до 99 включительно (генерируются случайным образом).
 * В примере, описанном выше, это путь 7, 3, 8, 7, 5, дающий
 * максимальную сумму 30.
 * Программа должна выводить на экран треугольник и
 * путь, который даст максимальный результат. Для текущего
 * примера он будет такой – влево, влево, вправо, влево.
 */
public class Task04 {
    /**
     * Метод создает и возвращает многомерный массив (треугольник).
     *
     * @param rows задает количество строк.
     */
    public static int[][] createTriangle(int rows) {
        Random rm = new Random();
        int minValue = 0;
        int maxValue = 100;
        int[][] triangle = new int[rows][];
        int numberOfElements = 1;

        for (int i = 0; i < rows; i++) {
            triangle[i] = new int[numberOfElements];
            for (int j = 0; j < triangle[i].length; j++) {
                triangle[i][j] = rm.nextInt(minValue, maxValue);
            }
            numberOfElements++;
        }

        return triangle;
    }

    /**
     * Метод выводит в консоль треугольник.
     */
    public static void printTriangle(int[][] triangleArray) {
        for (int[] row : triangleArray) {
            int numberOfTabulations = triangleArray.length - row.length;
            while (numberOfTabulations > 0) {
                System.out.print("\t");
                numberOfTabulations--;
            }
            for (int element : row) {
                System.out.printf("%d\t\t", element);
            }
            System.out.println("\n");
        }
    }

    public static void maxSumWay(int[][] triangleArray) {
        int[][] temp = cloneArray(triangleArray);
        int maxSum;
        //разбиваем треугольник на более мелкие и снизу вверх вычисляем наибольшие вершины,
        //результат записываем сохраняем в клоне исходного массива. Вершина клона и будет наибольшей суммой чисел
        //через которые проходит путь, начинающийся на вершине и заканчивающийся где-то на основании.
        for (int i = temp.length - 2; i >= 0; i--) {
            for (int j = 0; j < temp[i].length; j++) {
                temp[i][j] += Math.max(temp[i + 1][j], temp[i + 1][j + 1]);
            }
        }
        maxSum = temp[0][0];
        System.out.println("Наибольшая сумма чисел: " + maxSum);

        //Проходим сверху вниз по наибольшим вершинам заполненного клона и выводим на консоль путь.
        //Так же помечаем элементы, через которые проходит путь, значением -1 (это нужно будет далее, когда мы
        //будем подкрашивать вершины при выводе треугольника)
        int maxIndex = 0;
        temp[0][0] = -1;

        System.out.print("Путь: ");
        for (int i = 1; i < temp.length; i++) {
            if (temp[i][maxIndex] < temp[i][maxIndex + 1]) {
                maxIndex++;
                temp[i][maxIndex] = -1;
                System.out.print("вправо");
            } else {
                temp[i][maxIndex] = -1;
                System.out.print("влево");
            }
            if (i < temp.length - 1) System.out.print(", ");
        }
        System.out.println();

        //Выводим в консоль треугольник с подкрашенными элементами, через которые проходит путь
        for (int i = 0; i < triangleArray.length; i++) {
            int numberOfTabulations = triangleArray.length - triangleArray[i].length;

            while (numberOfTabulations > 0) {
                System.out.print("\t");
                numberOfTabulations--;
            }
            for (int j = 0; j < triangleArray[i].length; j++) {
                if (temp[i][j] == -1) {
                    System.out.printf("\u001B[31m%d\t\t\u001B[0m", triangleArray[i][j]);
                } else System.out.printf("%d\t\t", triangleArray[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Метод возвращает клон массива (даже если длина каждой строки разная).
     */
    private static int[][] cloneArray(int[][] array) {
        int[][] clone = new int[array.length][];

        for (int i = 0; i < array.length; i++) {
            clone[i] = new int[array[i].length];
            System.arraycopy(array[i], 0, clone[i], 0, array[i].length);
        }

        return clone;
    }
}
