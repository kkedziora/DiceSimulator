package com.example.dice.roll.simulation;

import java.security.SecureRandom;

class Dice {

    private final SecureRandom secureRandom;
    private final int numberOfSides;

    Dice(int numberOfSides) {
        secureRandom = new SecureRandom();
        this.numberOfSides = numberOfSides;
    }

    int roll() {
        return secureRandom.nextInt((this.numberOfSides - 1) + 1)  + 1 ;
    }
}
