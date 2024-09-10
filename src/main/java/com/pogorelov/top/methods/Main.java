package com.pogorelov.top.methods;

import com.pogorelov.top.methods.tasks.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nTask01");
        int size = 3;
        int value = 2;//некоторое случайное значение
        int[][] matrixA = {
                {1, 2, 1},
                {4, 2, 2},
                {0, 1, 7}};
        int[][] matrixB = {
                {7, 5, 1},
                {2, 1, 2},
                {4, 3, 4}};

        System.out.println("\nДиагональная матрица:");
        int[][] diagonalMatrix = Task01.createDiagonalMatrix(size, value);
        Task01.printMatrix(diagonalMatrix);

        System.out.println("\nНулевая матрица:");
        int[][] nullMatrix = Task01.createNullMatrix(size);
        Task01.printMatrix(nullMatrix);

        System.out.println("\nСложение двух матриц:");
        int[][] addition = Task01.additionMatrix(matrixA, matrixB);
        Task01.printMatrix(addition);

        System.out.println("\nУмножение двух матриц:");
        int[][] multTwoMatrix = Task01.multiplyOfTwoMatrix(matrixA, matrixB);
        Task01.printMatrix(multTwoMatrix);

        System.out.println("\nУмножение матрицы на скаляр:");
        int[][] multMatrixByNum = Task01.multiplyMatrixByNumber(matrixA, value);
        Task01.printMatrix(multMatrixByNum);

        System.out.println("\nОпределение детерминанта(определителя) матрицы:");
        Task01.printMatrix(matrixA);
        System.out.println("Детерминант(определитель) = " + Task01.detMatrix(matrixA));


        System.out.println("\n\n\nTask02");
        int[] arrayInt = {1, 2, 3, 4, 5};
        String[] arrayString = {"Раз", "да", "три", "четыре", "пять"};
        int[][] twoArrayInt = {
                {1, 2, 3},
                {4, 5, 6}};
        float[][] twoArrayFloat = {
                {1f, 2f, 3f},
                {4f, 5f, 6f}};

        System.out.println("\nint[] array:");
        Task02.printArray(arrayInt);

        System.out.println("\nString[] array:");
        Task02.printArray(arrayString);

        System.out.println("\nint[][] array:");
        Task02.printArray(twoArrayInt);

        System.out.println("\nfloat[][] array:");
        Task02.printArray(twoArrayFloat);


        System.out.println("\n\n\nTask03");
        String[] array1 = {"abbd", "Cbde", "Abzd", "czde", "AAbb", "dfea", "acad"};
        String[] array2 = {"|44 44 44 44|", "|11|", "|33 33 33|", "|22 22|"};

        System.out.println("\nВывод содержимого массива в строку через пробел:");
        Task03.printArray(array1);
        Task03.printArray(array2);

        System.out.println("\nСортировка массива в обратном порядке от z до a:");
        Task03.printArray(array1);
        Task03.sortArrayReverse(array1);
        Task03.printArray(array1);

        System.out.println("\nСортировка массива по количеству слов в строке (разделенных пробелом): ");
        Task03.printArray(array2);
        Task03.sortArrayByNumberOfWord(array2);
        Task03.printArray(array2);

        //TODO выяснить про доп задание (не понял задачу)


        System.out.println("\n\n\nTask04");
        int rows = 6;
        int[][] triangle = Task04.createTriangle(rows);

        System.out.println("\nТреугольник, заполненный случайными числами от 0 до 99:");
        Task04.printTriangle(triangle);
        System.out.println("\nРешение с визуальной схемой пути:");
        Task04.maxSumWay(triangle);


        System.out.println("\n\n\nTask05");
        int dragonHealth = 800;
        int dragonAttack = 36;
        int spearmanHealth = 15;
        int spearmanAttack = 10;
        Task05 task05 = new Task05(dragonHealth, dragonAttack, spearmanHealth, spearmanAttack);
        task05.runFight();


        System.out.println("\n\n\nTask06");
        String incorrect = "[ a)b ([c] d)])";
        String correct = "(a[b](f[(g)(g)]))";

        System.out.println(incorrect);
        Task06.checkBrackets(incorrect);
        System.out.println();

        System.out.println(correct);
        Task06.checkBrackets(correct);


    }
}
