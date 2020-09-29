package com.example.dice.roll.simulation;

import lombok.Getter;

import java.util.List;

@Getter
public class SimulationResponse {

    private final List<RollResult> listOfRollDice;

    public SimulationResponse(List<RollResult> rollResults) {
        this.listOfRollDice = rollResults;
    }

}
