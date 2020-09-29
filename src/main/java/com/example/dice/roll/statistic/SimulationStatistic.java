package com.example.dice.roll.statistic;


import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class SimulationStatistic {

    private final Integer numberOfSides;
    private final Integer numberOfDices;
    private final Long count;
    private final Long totalNumberOfRolls;

    public SimulationStatistic(Integer numberOfSides, Integer numberOfDices, Long count, Long totalNumberOfRolls) {
        this.numberOfSides = numberOfSides;
        this.numberOfDices = numberOfDices;
        this.count = count;
        this.totalNumberOfRolls = totalNumberOfRolls;
    }
}
