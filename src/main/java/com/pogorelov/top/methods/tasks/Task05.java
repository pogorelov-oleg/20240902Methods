package com.pogorelov.top.methods.tasks;

/**
 * 5. Королю нужно убить дракона, но в его казне мало средств
 * для покупки армии. Нужно создать программу, используя
 * методы, которая поможет рассчитать минимальное количество копейщиков, которое необходимо, чтобы убить
 * дракона. C клавиатуры вводятся данные:
 * ■ здоровья дракона;
 * ■ атаки дракона;
 * ■ здоровье одного копейщика;
 * ■ атака одного копейщика.
 * Защита, меткость и т. п. не учитывать. Копейщики наносят
 * удар первыми (общий нанесенный урон – это сумма атак
 * всех живых копейщиков). Если атака дракона превышает
 * значение жизни копейщика (например, у копейщика жизни – 10, а атака – 15), то умирает несколько копейщиков, а
 * оставшийся урон идет одному из копейщиков.
 * Например, жизнь дракона – 500, атака – 55, жизнь одного копейщика – 10, атака –10, а количество копейщиков при
 * данных условиях – 20.
 * Лог боя для данного примера должен выглядеть так:
 * Итерация 15
 * Копейщики атакуют (урон 200) – у дракона осталось 300 жизней
 * Дракон атакует – осталось 15 копейщиков, один из которых ранен
 * (осталось 5 жизней)
 * Копейщики атакуют – у дракона осталось 150 жизней
 * Дракон атакует – осталось 9 копейщиков
 * Копейщики атакуют – у дракона осталось 60 жизней
 * Дракон атакует – осталось 4 копейщика, один из которых ранен
 * (осталось 5 жизней)
 * Копейщики атакуют – у дракона осталось 20 жизней
 * Дракон атакует и побеждает
 */
public class Task05 {
    private int totalSpearmanHealth;
    private int dragonHealth;
    private final int dragonAttack;
    private final int spearmanHealth;
    private final int spearmanAttack;
    private boolean isSpearmanMove = true;

    /**
     * Конструктор.
     */
    public Task05(int dragonHealth, int dragonAttack, int spearmanHealth, int spearmanAttack) {
        this.dragonHealth = dragonHealth;
        this.dragonAttack = dragonAttack;
        this.spearmanHealth = spearmanHealth;
        this.spearmanAttack = spearmanAttack;
    }

    /**
     * Метод запускает бой и выводит подробный лог боя в консоль.
     */
    public void runFight() {
        boolean flag = true;

        while (flag) {
            if (isSpearmanMove) {
                if (totalSpearmanHealth > 0) {
                    spearmanMove();
                    showAllUnitsInfo();
                    isSpearmanMove = false;
                    if (dragonHealth <= 0) {
                        System.out.println("Копейщики победили\n");
                        flag = false;
                    }
                } else {
                    System.out.println("У вас нет копейщиков для нападения");
                    flag = false;
                }
            } else {
                dragonMove();
                showAllUnitsInfo();
                isSpearmanMove = true;
                if (totalSpearmanHealth <= 0) {
                    System.out.println("Дракон победил\n");
                    flag = false;
                }
            }
        }
    }

    /**
     * Запускает ход копейщика.
     */
    private void spearmanMove() {
        System.out.println("\u001B[34mХОД КОПЕЙЩИКОВ:\u001B[0m");
        dragonHealth -= getNumberOfSpearman() * spearmanAttack;
        System.out.printf("Копейщики наносят урон %d\n",
                getNumberOfSpearman() * spearmanAttack);
        if (dragonHealth < 0) dragonHealth = 0;
    }

    /**
     * Запускает ход дракона.
     */
    private void dragonMove() {
        int numberOfSpearmanBefore = getNumberOfSpearman();

        System.out.println("\u001B[31mХОД ДРАКОНА:\u001B[0m");
        totalSpearmanHealth -= dragonAttack;
        System.out.printf("Дракон наносит урон %d. %d копейщиков погибают\n",
                dragonAttack, numberOfSpearmanBefore - getNumberOfSpearman());
        if (totalSpearmanHealth < 0) totalSpearmanHealth = 0;
    }

    /**
     * Метод выводит информацию о здоровье дракона и количестве копейщиков.
     */
    private void showAllUnitsInfo() {
        if (dragonHealth <= 0) System.out.printf("\u001B[32mДракон: УБИТ!\nКопейщики: %s\u001B[0m",
                spearmanInfo());
        else if (totalSpearmanHealth <= 0)
            System.out.printf("\u001B[32mДракон: %d здоровья\nКопейщики: УБИТЫ!\u001B[0m",
                    dragonHealth);
        else
            System.out.printf("\u001B[32mДракон: %d здоровья\nКопейщики: %s\u001B[0m",
                    dragonHealth, spearmanInfo());
        System.out.println("\n");
    }

    /**
     * Метод вычисляет количество целых копейщиков и остаток здоровья у раненого (если такой есть).
     * Результат возвращает в виде строки.
     */
    private String spearmanInfo() {
        String spearmanInfo;
        int whole = totalSpearmanHealth / spearmanHealth;
        int wounded = totalSpearmanHealth - whole * spearmanHealth;

        if (wounded > 0)
            spearmanInfo = String.format("%d + 1 раненый(%dhp)", whole, wounded);
        else spearmanInfo = String.format("%d", whole);

        return spearmanInfo;
    }

    /**
     * Метод рассчитывает и возвращает необходимое минимальное количество копейщиков,
     * необходимых для победы над драконом.
     */
    public int numberOfSpearmanCalculator() {
        int result = 0;
        int dragonHealthTemp;
        boolean flag = false;

        while (!flag) {
            flag = true;
            result++;
            dragonHealthTemp = dragonHealth;
            totalSpearmanHealth = result * spearmanHealth;

            while (true) {
                dragonHealthTemp -= getNumberOfSpearman() * spearmanAttack;
                if (dragonHealthTemp <= 0) break;
                totalSpearmanHealth -= dragonAttack;
                if (totalSpearmanHealth <= 0) break;
            }

            if (dragonHealthTemp > 0) flag = false;
        }

        return result;
    }

    /**
     * Метод рассчитывает общее количество бойцов (с учетом раненых) и возвращает его значение.
     */
    private int getNumberOfSpearman() {
        int numberOfSpearman = totalSpearmanHealth / spearmanHealth;

        if (totalSpearmanHealth < 0)
            return 0;
        if (totalSpearmanHealth % spearmanHealth != 0) numberOfSpearman++;

        return numberOfSpearman;
    }

    /**
     * Метод принимает значение количиства копейщиков и устанавливает
     * параметр totalSpearmanHealth (общее здоровье копейщиков).
     */
    public void setNumberOfSpearman(int numberOfSpearman) {
        this.totalSpearmanHealth = numberOfSpearman * this.spearmanHealth;
    }
}
