package com.example.dice.roll.statistic;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class RelativeDistribution {

    private final Integer value;
    private final BigDecimal percentage;

    public RelativeDistribution(Integer value, BigDecimal percentage) {
        this.value = value;
        this.percentage = percentage.setScale(2, RoundingMode.FLOOR);
    }

}
