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
    int dragonHealth;
    int dragonAttack;
    int spearmanHealth;
    int spearmanAttack;
    int numberOfSpearman = 15;
    int woundedSpearmanHealth = 0;
    boolean isSpearmanMove = true;

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
     * Метод запускает бой.
     */
    public void runFight() {
        boolean flag = true;
        while (flag) {
            if (isSpearmanMove) {
                if (numberOfSpearman > 0) {
                    spearmanMove();
                    showInfo();
                    isSpearmanMove = false;
                    if (dragonHealth <= 0) {
                        System.out.println("Копейщики победили\n");
                        flag = false;
                    }
                } else System.out.println("У вас нет копейщиков для нападения");
            } else {
                dragonMove();
                showInfo();
                isSpearmanMove = true;
                if (numberOfSpearman <= 0) {
                    System.out.println("Дракон победил\n");
                    flag = false;
                }
            }
        }
    }

    /**
     * Метод выводит информацию о здоровье дракона и количестве копейщиков.
     */
    public void showInfo() {
        if (dragonHealth <= 0) System.out.printf("\u001B[32mДракон: УБИТ!\nКопейщики: осталось %d единиц\u001B[0m",
                numberOfSpearman);
        else if (numberOfSpearman < 0 || numberOfSpearman == 0 && woundedSpearmanHealth == 0)
            System.out.printf("\u001B[32mДракон: осталось %d здоровья\nКопейщики: УБИТЫ!\u001B[0m",
                    dragonHealth);
        else
            System.out.printf("\u001B[32mДракон: осталось %d здоровья\nКопейщики: осталось %d единиц\u001B[0m",
                    dragonHealth, numberOfSpearman);
        if (woundedSpearmanHealth > 0 && numberOfSpearman >= 0)
            System.out.printf("\u001B[33m (у 1 из них осталось %d здоровья)\u001B[0m", woundedSpearmanHealth);
        System.out.println("\n");
    }


    public void spearmanMove() {
        System.out.println("\u001B[34mХОД КОПЕЙЩИКОВ:\u001B[0m");
        dragonHealth -= numberOfSpearman * spearmanAttack;
        System.out.printf("Копейщики наносят урон %d\n",
                numberOfSpearman * spearmanAttack);
        System.out.println();
    }

    public void dragonMove() {
        int numberOfSpearmanKilled = dragonAttack / spearmanHealth;//количество убитых копейщиков
        int restOfTheAttack = dragonAttack - numberOfSpearmanKilled * spearmanHealth;//остаток атаки

        System.out.println("\u001B[31mХОД ДРАКОНА:\u001B[0m");
        //если небыло раненых, рассчитываем сколько здоровья останется после удара у раненного (if restOfTheAttack > 0)
        if (woundedSpearmanHealth == 0) {
            woundedSpearmanHealth = spearmanHealth - restOfTheAttack;
            //если были раненые:
        } else if (woundedSpearmanHealth > 0) {
            //если здровье раненого выше чем restOfTheAttack, просто отнимаем
            if (woundedSpearmanHealth > restOfTheAttack) woundedSpearmanHealth -= restOfTheAttack;
            //если здоровья столько же или меньше, добавляем к показателю numberOfSpearmanKilled еще единицу и
            //заново вычисляем значение раненых.
            else {
                numberOfSpearmanKilled++;
                woundedSpearmanHealth = woundedSpearmanHealth - restOfTheAttack + spearmanHealth;
                //если
                if (woundedSpearmanHealth == spearmanHealth) woundedSpearmanHealth = 0;
            }
        }
        numberOfSpearman -= numberOfSpearmanKilled;
        System.out.println("rrr="+numberOfSpearman);
        if (numberOfSpearman >= numberOfSpearmanKilled) {
            System.out.printf("Дракон наносит урон %d. %d копейщиков погибают\n",
                    dragonAttack, numberOfSpearmanKilled);
        } else {
            System.out.printf("Дракон наносит урон %d. %d копейщиков погибают\n",
                    dragonAttack, numberOfSpearman + numberOfSpearmanKilled);//прибавляю обратно numberOfSpearmanKilled,
            // чтобы вывод в консоль был коректен и количество убитых не ушло в минус
            numberOfSpearman = 0;
            woundedSpearmanHealth = 0;
        }
        System.out.println();
    }
}
