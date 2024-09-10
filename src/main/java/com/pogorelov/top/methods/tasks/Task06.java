package com.pogorelov.top.methods.tasks;

/**
 * 6. Напишите метод, проверяющую правильность расстановки скобок в строке, введенной с клавиатуры.
 * При правильной расстановке выполняются условия:
 * ■ количество открывающих и закрывающих скобок равно;
 * ■ внутри любой пары открывающая–соответствующая
 * закрывающая скобка, скобки расставлены правильно.
 * В строке могут присутствовать как круглые, так и квадратные скобки (и др. символы). Каждой открывающей
 * скобке соответствует закрывающая того же типа (круглой –
 * круглая, квадратной – квадратная).
 * Пример неправильной расстановки: ( [ a) b]
 * Пример правильных входных данных: (a[b](f[(g)(g)]))
 * Программа должна вывести результат в виде сообщения,
 * примеры:
 * ■ Правильная строка
 * ■ Ошибка отсутствие (
 * ■ Ошибка отсутствие (
 * ■ Ошибка отсутствие [
 * ■ Ошибка отсутствие ]
 */
public class Task06 {
    /**
     * Метод принимает строку и проверяет корректность рассановки скобок.
     * Если все верно то вывод сопровождается соответствующим сообщением.
     * В случае если скобки расставлены некорректно, это помечается в строке красным цветом и выводится в консоль.
     */
    public static void checkBrackets(String string) {
        int[] arrayToCheck = getArrayToCheck(string);
        if (noZero(arrayToCheck)) System.out.println("Правильная строка.");
        else {
            System.out.print("ОШИБКА! Исправьте красные скобки: ");
            for (int i = 0; i < string.length(); i++) {
                if (arrayToCheck[i] == 0) {
                    System.out.print("\u001B[31m" + string.charAt(i) + "\u001B[0m");
                } else System.out.print(string.charAt(i));
            }
            System.out.println();
        }
    }

    /**
     * Метод принимает строку и создает целочисленный массив равный длине строки. Далее метод проверяет каждый
     * символ строки и если он корректный, то записывает 1 в тот же индекс целочисленного массива. Таким образом
     * массив полностью заполняется 1 там, где данные введены корректно. Если данные были введены некоректно,
     * в массив ничего не записывается и по умолчанию это значение остается равно 0.
     *
     * @return Возвращает заполненый целочисленный массив, где 0 - некорректные данные, 1 - корректные данные.
     */
    private static int[] getArrayToCheck(String string) {
        int[] arrayToCheck = new int[string.length()];
        int startIndex = 0;
        char startChar = '*';
        boolean flag = false;

        while (!flag) {
            flag = true;
            for (int i = 0; i < string.length(); i++) {
                if (arrayToCheck[i] != 0) {
                    continue;
                }
                if (string.charAt(i) != '(' && string.charAt(i) != ')' &&
                        string.charAt(i) != '[' & string.charAt(i) != ']')
                    arrayToCheck[i] = 1;
                if (string.charAt(i) == '(' || string.charAt(i) == '[') {
                    startChar = string.charAt(i);
                    startIndex = i;
                    continue;
                }
                switch (startChar) {
                    case '(':
                        if (string.charAt(i) == ']') {
                            startChar = '*';
                            break;
                        }
                        if (string.charAt(i) == ')') {
                            arrayToCheck[startIndex] = 1;
                            arrayToCheck[i] = 1;
                            startChar = '*';
                            flag = false;
                            break;
                        }
                    case '[':
                        if (string.charAt(i) == ')') {
                            startChar = '*';
                            break;
                        }
                        if (string.charAt(i) == ']') {
                            arrayToCheck[startIndex] = 1;
                            arrayToCheck[i] = 1;
                            startChar = '*';
                            flag = false;
                            break;
                        }
                }
            }
        }
        return arrayToCheck;
    }

    /**
     * Метод проверяет массив на наличие нулей и возвращает true если их нет.
     */
    private static boolean noZero(int[] array) {
        boolean noZero = true;
        for (int element : array)
            if (element == 0) {
                noZero = false;
                break;
            }
        return noZero;
    }
}