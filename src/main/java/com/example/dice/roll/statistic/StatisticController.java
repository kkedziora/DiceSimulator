package com.example.dice.roll.statistic;

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
@RequestMapping(value = "/api/dices/statistics", produces = APPLICATION_JSON_VALUE)
@Validated
public class StatisticController {

    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/summaries")
    public ResponseEntity<List<SimulationStatistic>> getStatistics(){
        List<SimulationStatistic> statistics = statisticService.calculateTotalNumberOfSimulationsAndTotalRollsMade();

        return ResponseEntity.ok(statistics);
    }


    @GetMapping("/distributions")
    public ResponseEntity<List<RelativeDistribution>> getStatistics(
            @RequestParam(name = "numberOfDices") @Min(1) int numberOfDices,
            @RequestParam(name = "numberOfSides") @Min(4) int numberOfSides){
        List<RelativeDistribution> relativeDistributions = statisticService.calculateDistribution(numberOfDices, numberOfSides);

        return ResponseEntity.ok(relativeDistributions);
    }
}
