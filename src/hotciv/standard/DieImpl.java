package hotciv.standard;

import hotciv.framework.Die;

public class DieImpl implements Die {

    public int rollDie() {
        return (int) (Math.random() * 6) + 1;
    }
}
