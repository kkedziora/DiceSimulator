package com.example.dice.roll.repository;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "RESULTS")
@NoArgsConstructor
public class Result implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "simulation_id", nullable = false)
    private Simulation simulation;

    public Result(Simulation simulation, Integer value) {
        this.simulation = simulation;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
