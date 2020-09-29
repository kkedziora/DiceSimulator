package com.example.dice.roll.simulation;

import com.example.dice.roll.repository.Simulation;
import com.example.dice.roll.repository.SimulationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DiceRollerSimulatorTest {


    @InjectMocks
    private DiceRollService diceRollService;

    @Mock
    private SimulationRepository repository;

    @Test
    void simulateRollDices() {
        RollParameters rollParameters = new RollParameters(100, 3, 6);
        List<RollResult> rollResults = diceRollService.simulateRollDices(rollParameters);

        assertThat(rollResults).hasSizeBetween(1, 100);
        verify(repository, times(1)).save(any(Simulation.class));
    }
}