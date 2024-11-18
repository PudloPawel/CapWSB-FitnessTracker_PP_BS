package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticService;
import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticServiceImpl implements StatisticsProvider, StatisticService {

    private final StatisticRepository statisticRepository;

    public Statistics addStatistics(Statistics statistic) {
        log.info("Creating Training {}", statistic);
        if (statistic.getId() != null) {
            throw new IllegalArgumentException("Training has already DB ID, update is not permitted!");
        }

        return statisticRepository.save(statistic);
    }


    @Override
    public Optional<Statistics> getStatistics(Long statisticsId) {
        return Optional.empty();
    }
}

