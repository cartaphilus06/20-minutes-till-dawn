package com.tilldawn.Models;

import java.util.Random;

public class RandomNum {
    private final static Random rand = new Random();
    public static int getRandomNumber(int min, int max) {
        return rand.nextInt(min,max);
    }
}
