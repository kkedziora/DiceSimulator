package com.example.dice.roll.simulation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GroupedDiceRollTest {

    @ParameterizedTest
    @MethodSource("getTestValues")
    void shouldRollDices_getAllRolledSum(int numberOfRolls, int numberOfDices, int numberOfSides) {

        RollParameters rollParameters = new RollParameters(numberOfRolls, numberOfDices, numberOfSides);
        GroupedDiceRoll groupedDiceRoll = new GroupedDiceRoll(rollParameters);
        groupedDiceRoll.rollDices();
        List<Integer> listOfRolledSum = groupedDiceRoll.getAllRolledSum();

        Predicate<Integer> allowedRange = value -> value >= numberOfDices && value <= (numberOfDices * numberOfSides);
        assertThat(listOfRolledSum).filteredOn(allowedRange).hasSize(numberOfRolls);
    }

    @Test
    void shouldRollDices_getResultByCount() {
        RollParameters parameters = new RollParameters(10, 3, 6);
        GroupedDiceRoll groupedDiceRoll = new GroupedDiceRoll(parameters);
        groupedDiceRoll.rollDices();
        Map<Integer, Long> resultByCount = groupedDiceRoll.getResultByCount();

        assertThat(resultByCount)
                .isNotEmpty()
                .hasSizeBetween(3, 18)
                .doesNotContainKeys(0, 1, 2, 19, 20);

    }

    private static Stream<Arguments> getTestValues() {
        return Stream.of(
                Arguments.of(1, 1, 4),
                Arguments.of(100, 3, 6),
                Arguments.of(10, 10, 6),
                Arguments.of(1000, 100, 10)
        );
    }
}