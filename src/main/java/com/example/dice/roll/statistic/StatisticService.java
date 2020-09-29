package com.example.dice.roll.statistic;

import com.example.dice.roll.repository.Result;
import com.example.dice.roll.repository.Simulation;
import com.example.dice.roll.repository.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class StatisticService {

    private final SimulationRepository repository;

    @Autowired
    public StatisticService(SimulationRepository repository) {
        this.repository = repository;
    }

    public List<SimulationStatistic> calculateTotalNumberOfSimulationsAndTotalRollsMade() {
        return repository.calculateTotalNumberAndTotalRollsMadeByDiceNumberAndDiceSide();
    }

    public List<RelativeDistribution> calculateDistribution(int numberOfDices, int numberOfSides) {

        List<Simulation> simulations = repository.findResultsByNumberOfDicesAndNumberOfSides(numberOfDices, numberOfSides);
        List<Integer> allResults = simulations.stream()
                .flatMap(simulation -> simulation.getResults().stream())
                .map(Result::getValue)
                .collect(Collectors.toList());

        if(simulations.isEmpty()){
            return new ArrayList<>();
        }

        Map<Integer, Long> resultByCount = getResultByCount(allResults);

        int total = allResults.size();

        return resultByCount.entrySet().stream()
                .map(e -> calculateDistribution(e, total))
                .sorted(Comparator.comparing(RelativeDistribution::getValue))
                .collect(Collectors.toList());
    }

    private RelativeDistribution calculateDistribution(Map.Entry<Integer, Long> entry, int total) {
        double percentage = entry.getValue() * 100f / total;
        return new RelativeDistribution(entry.getKey(), BigDecimal.valueOf(percentage));
    }

    private Map<Integer, Long> getResultByCount(List<Integer> result) {
        return result.stream()
                .collect(Collectors.groupingBy(value -> value, Collectors.counting()));
    }
}
