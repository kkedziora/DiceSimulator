package com.example.dice.roll.simulation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class DiceTest {

    @ParameterizedTest
    @ValueSource(ints = {4,10,1000, Integer.MAX_VALUE})
    void roll(int numberOfSides) {
        Dice dice = new Dice(numberOfSides);
        int result = dice.roll();
        assertThat(result).isBetween(1, numberOfSides);
    }
}