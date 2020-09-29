package com.example.dice.roll.statistic;

import com.example.dice.roll.repository.Simulation;
import com.example.dice.roll.repository.SimulationRepository;
import com.example.dice.roll.simulation.RollParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticServiceTest {

    @InjectMocks
    private StatisticService statisticService;
    @Mock
    private SimulationRepository repository;

    @Test
    void shouldCalculateTotalNumberOfSimulationsAndTotalRollsMade() {

        List<SimulationStatistic> result = new ArrayList<>();
        result.add(new SimulationStatistic(4, 3, 2L, 300L));
        result.add(new SimulationStatistic(6, 10, 3L, 3000L));
        result.add(new SimulationStatistic(6, 100, 4L, 2000L));
        result.add(new SimulationStatistic(6, 200, 5L, 30000L));
        when(repository.calculateTotalNumberAndTotalRollsMadeByDiceNumberAndDiceSide()).thenReturn(result);
        List<SimulationStatistic> simulationStatistics = statisticService.calculateTotalNumberOfSimulationsAndTotalRollsMade();

        assertThat(simulationStatistics).containsAll(result);
    }

    @Test
    void shouldCalculateDistribution() {
        int numberOfDices = 3;
        int numberOfSides = 6;
        RollParameters parameters = new RollParameters(10, numberOfDices, numberOfSides);
        List<Simulation> simulations = new ArrayList<>();
        simulations.add(createSimulation(parameters, Arrays.asList(3, 4, 3, 6, 4, 10)));
        simulations.add(createSimulation(parameters, Arrays.asList(3, 4, 11, 4, 14, 18)));
        when(repository.findResultsByNumberOfDicesAndNumberOfSides(numberOfDices, numberOfSides)).thenReturn(simulations);

        List<RelativeDistribution> relativeDistributions = statisticService.calculateDistribution(numberOfDices, numberOfSides);
        BigDecimal sum = relativeDistributions.stream()
                .map(RelativeDistribution::getPercentage)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        assertThat(relativeDistributions).extracting("value").containsOnly(3, 4, 6, 10, 11, 14, 18);
        assertThat(sum.setScale(0, RoundingMode.UP)).isEqualTo(new BigDecimal("100"));
    }

    @Test
    void shouldCalculateDistribution_emptyResult() {
        int numberOfDices = 3;
        int numberOfSides = 6;
        when(repository.findResultsByNumberOfDicesAndNumberOfSides(numberOfDices, numberOfSides)).thenReturn(Collections.emptyList());

        List<RelativeDistribution> relativeDistributions = statisticService.calculateDistribution(numberOfDices, numberOfSides);

        assertThat(relativeDistributions).isEmpty();
    }

    private Simulation createSimulation(RollParameters parameters, List<Integer> results) {
        return new Simulation(parameters, results);
    }
}