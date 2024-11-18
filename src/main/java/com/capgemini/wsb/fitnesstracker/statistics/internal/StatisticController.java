package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v3")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticServiceImpl statisticService;
    private final StatisticMapper statisticMapper;

    @PostMapping(value = "/addStatistics")
    public Statistics addStatistic(@RequestBody StatisticDto statisticDto) {
        return statisticService.addStatistics(new Statistics(statisticDto.user(),
                statisticDto.totalTrainings(),
                statisticDto.totalDistance(),
                statisticDto.totalCaloriesBurned()));



    }

}
