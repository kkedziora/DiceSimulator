package com.example.dice.roll.repository;

import com.example.dice.roll.statistic.SimulationStatistic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SimulationRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SimulationRepository simulationRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(simulationRepository).isNotNull();
    }

    @Test
    @Sql(value = {"classpath:simulations.sql"})
    void calculateTotalNumberAndTotalRollsMadeByDiceNumberAndDiceSide() {
        List<SimulationStatistic> simulationStatistics = simulationRepository.calculateTotalNumberAndTotalRollsMadeByDiceNumberAndDiceSide();

        SimulationStatistic expected = new SimulationStatistic(6, 3, 2L, 15L);
        assertThat(simulationStatistics).containsOnly(expected);

    }


    @Test
    @Sql(value = {"classpath:simulations.sql"})
    void findResultsByNumberOfDicesAndNumberOfSides() {
        List<Simulation> result = simulationRepository.findResultsByNumberOfDicesAndNumberOfSides(3, 6);

        assertThat(result).hasSize(2);
        assertThat(result).extracting("numberOfRolls").contains(10, 5);
        assertThat(result).extracting("numberOfDices").containsOnly(3);
        assertThat(result).extracting("numberOfSides").containsOnly(6);
        assertThat(result).extracting("results").isNotEmpty();
    }
}