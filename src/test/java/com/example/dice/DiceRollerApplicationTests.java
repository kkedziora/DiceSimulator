package com.example.dice;

import com.example.dice.roll.simulation.SimulationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DiceRollerApplicationTests {

    @Autowired
    private SimulationController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
