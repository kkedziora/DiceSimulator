package com.example.dice.roll.simulation;

import lombok.Getter;

@Getter
public final class RollParameters {

    private final int numberOfRolls;
    private final int numberOfDices;
    private final int numberOfSides;

    public RollParameters(int numberOfRolls, int numberOfDices, int numberOfSides) {
        this.numberOfRolls = numberOfRolls;
        this.numberOfDices = numberOfDices;
        this.numberOfSides = numberOfSides;
    }

}
