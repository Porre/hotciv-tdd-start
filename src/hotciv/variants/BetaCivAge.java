package hotciv.variants;

import hotciv.framework.AgeStrategy;

public class BetaCivAge implements AgeStrategy {

    private int age;

    public BetaCivAge() {
        age = -4000;
    }

    public int getAge() {
        return age;
    }

    public void progressAge() {
        if (age < -100) {
            age = age + 100;
        } else if (age == -100) {
            age = -1;
        } else if (age == -1) {
            age = 1;
        } else if (age == 1) {
            age = 50;
        } else if (age >= 50 && age < 1750) {
            age += 50;
        } else if (age >= 1750 && age < 1900) {
            age += 25;
        } else if (age >= 1900 && age < 1970) {
            age += 5;
        } else if (age >= 1970) {
            age += 1;
        }
    }
}
