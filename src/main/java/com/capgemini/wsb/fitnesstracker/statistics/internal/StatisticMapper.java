package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import org.springframework.stereotype.Component;

@Component
public class StatisticMapper {

    StatisticDto toDto(Statistics statistic) {
        return new StatisticDto(statistic.getUser(),
                statistic.getTotalTrainings(),
                statistic.getTotalDistance(),
                statistic.getTotalCaloriesBurned());
    }

}
