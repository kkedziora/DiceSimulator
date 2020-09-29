package com.example.dice.roll.repository;

import com.example.dice.roll.statistic.SimulationStatistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimulationRepository extends CrudRepository<Simulation, Long> {

    @Query("SELECT new com.example.dice.roll.statistic.SimulationStatistic(s.numberOfSides, s.numberOfDices, count(s.numberOfRolls), sum(s.numberOfRolls)) " +
            "FROM Simulation AS s GROUP BY s.numberOfSides, s.numberOfDices")
    List<SimulationStatistic> calculateTotalNumberAndTotalRollsMadeByDiceNumberAndDiceSide();

    @Query("SELECT s FROM Simulation AS s WHERE s.numberOfDices=?1 and s.numberOfSides=?2")
    List<Simulation> findResultsByNumberOfDicesAndNumberOfSides(int numberOfDices, int numberOfSides);

}
