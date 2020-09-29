package com.example.dice.roll.repository;

import com.example.dice.roll.simulation.RollParameters;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "SIMULATIONS")
public class Simulation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMBER_OF_ROLLS")
    private Integer numberOfRolls;

    @Column(name = "NUMBER_OF_DICES")
    private Integer numberOfDices;

    @Column(name = "NUMBER_OF_SIDES")
    private Integer numberOfSides;

    @OneToMany(mappedBy = "simulation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Result> results = new ArrayList<>();

    public Simulation() { }

    public Simulation(RollParameters parameters, List<Integer> listOfSumRolledNumber) {
        List<Result> results = listOfSumRolledNumber.stream()
                .map(value -> new Result(this, value))
                .collect(Collectors.toList());
        this.numberOfRolls = parameters.getNumberOfRolls();
        this.numberOfDices = parameters.getNumberOfDices();
        this.numberOfSides = parameters.getNumberOfSides();
        this.results = results;
    }

    public Integer getNumberOfRolls() {
        return numberOfRolls;
    }

    public Integer getNumberOfDices() {
        return numberOfDices;
    }

    public Integer getNumberOfSides() {
        return numberOfSides;
    }

    public List<Result> getResults() {
        return results;
    }
}
