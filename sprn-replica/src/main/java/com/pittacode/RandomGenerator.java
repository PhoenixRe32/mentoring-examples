package com.pittacode;

import java.util.Arrays;
import java.util.List;

public class RandomGenerator {

    private static final List<Integer> RANDOMS = Arrays.asList(
            1804289383, 846930886, 1681692777, 1714636915, 1957747793, 424238335, 719885386, 1649760492,
            596516649, 1189641421, 1025202362, 1350490027, 783368690, 1102520059, 2044897763, 1967513926,
            1365180540, 1540383426, 304089172, 1303455736, 35005211, 521595368);

    private int randomIndex;

    public RandomGenerator() {
        this.randomIndex = 0;
    }

    public int getNext() {
        int nextRandom = RANDOMS.get(randomIndex);
        randomIndex = (randomIndex + 1) % RANDOMS.size();
        return nextRandom;
    }
}
