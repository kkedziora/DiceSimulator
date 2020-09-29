package com.example.dice.roll.simulation;

import com.example.dice.roll.repository.Simulation;
import com.example.dice.roll.repository.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
class DiceRollService {

    private final SimulationRepository repository;

    @Autowired
    public DiceRollService(SimulationRepository repository) {
        this.repository = repository;
    }

    List<RollResult> simulateRollDices(RollParameters parameters) {
        GroupedDiceRoll groupedDiceRoll = new GroupedDiceRoll(parameters);
        groupedDiceRoll.rollDices();
        saveSimulation(parameters, groupedDiceRoll.getAllRolledSum());

        return groupedDiceRoll.getResultByCount().entrySet().stream()
                .map(r -> new RollResult(r.getKey(), r.getValue()))
                .sorted(Comparator.comparing(RollResult::getResult))
                .collect(Collectors.toList());
    }

    private void saveSimulation(RollParameters parameters, List<Integer> result) {
        Simulation simulation = new Simulation(parameters, result);
        repository.save(simulation);
    }

}
