package com.pogorelov.top.methods.tasks;

/**
 * 3. В массиве хранится n явно заданных текстовых строк. Создать метод:
 * ■ выводящий содержимое массива в строку через пробел;
 * ■ сортирующий массив в обратном порядке (без учета
 * регистра) от z до a;
 * ■ сортирующий массив по количеству слов в строке (слова
 * разделены пробелами).
 * Программа должна вывести строки в начальном и отсортированном порядке.
 * Дополнительно: 1 балл за генерацию случайных уникальных строк реализованных в виде метода.
 */
public class Task03 {
    /**
     * Метод выводит в консоль одномерный массив типа String через пробел.
     */
    public static void printArray(String[] array) {
        for (String element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /**
     * Метод сортирует массив в обратном порядке от z до a.
     */
    public static void sortArrayReverse(String[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                int charIndex = 0;
                while ((array[j].toLowerCase().charAt(charIndex) == array[j + 1].toLowerCase().charAt(charIndex)))
                    charIndex++;
                if (array[j].toLowerCase().charAt(charIndex) < array[j + 1].toLowerCase().charAt(charIndex)) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Метод сортирует массив по количеству слов в строке (разделенных пробелом).
     */
    public static void sortArrayByNumberOfWord(String[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {

                if (array[j].split(" ").length > array[j + 1].split(" ").length) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
