package com.example.dice.roll.simulation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/dices", produces = APPLICATION_JSON_VALUE)
@Validated
public class SimulationController {

    private final DiceRollService diceRollService;

    @Autowired
    public SimulationController(DiceRollService diceRollService) {
        this.diceRollService = diceRollService;
    }

    @GetMapping("/rolls")
    public ResponseEntity<?> runRollDicesSimulation(
            @RequestParam(name = "numberOfRolls") @Min(0) int numberOfRolls,
            @RequestParam(name = "numberOfDices") @Min(1) int numberOfDices,
            @RequestParam(name = "numberOfSides") @Min(4) int numberOfSides) {

        RollParameters rollParameters = new RollParameters(numberOfRolls, numberOfDices, numberOfSides);
        List<RollResult> rollResults = diceRollService.simulateRollDices(rollParameters);

        return ResponseEntity.ok(new SimulationResponse(rollResults));
    }

}
