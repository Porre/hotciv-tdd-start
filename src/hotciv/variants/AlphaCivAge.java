package hotciv.variants;

import hotciv.framework.AgeStrategy;

public class AlphaCivAge implements AgeStrategy {

    private int age;

    public AlphaCivAge() {
        age = -4000;
    }

    public int getAge() {
        return age;
    }

    public void progressAge() {
        age = age + 100;
    }
}