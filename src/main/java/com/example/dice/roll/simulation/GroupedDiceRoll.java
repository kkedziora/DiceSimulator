package com.example.dice.roll.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GroupedDiceRoll {

    private final RollParameters parameters;
    private final List<Integer> listOfRolledSum;

    GroupedDiceRoll(RollParameters parameters) {
        this.parameters = parameters;
        this.listOfRolledSum = new ArrayList<>();
    }

    void rollDices() {
        List<Integer> result = generateRolledSum();
        this.listOfRolledSum.addAll(result);
    }

    Map<Integer, Long> getResultByCount() {
        return listOfRolledSum.stream()
                .collect(Collectors.groupingBy(value -> value, Collectors.counting()));
    }

    List<Integer> getAllRolledSum() {
        return listOfRolledSum;
    }

    private List<Integer> generateRolledSum() {
        return IntStream.rangeClosed(1, parameters.getNumberOfRolls())
                .map(operand -> getRolledSum(parameters)).boxed()
                .collect(Collectors.toList());
    }

    private int getRolledSum(RollParameters parameters) {
        Dice dice = new Dice(parameters.getNumberOfSides());

        return IntStream.rangeClosed(1, parameters.getNumberOfDices())
                .map(value -> dice.roll())
                .reduce(0, Integer::sum);
    }
}
