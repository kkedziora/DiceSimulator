package com.example.dice.roll.simulation;

import lombok.Getter;

@Getter
public class RollResult {

    private final Integer result;
    private final Long count;

    public RollResult(Integer result, Long count) {
        this.result = result;
        this.count = count;
    }

}
