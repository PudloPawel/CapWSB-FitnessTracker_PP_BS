package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;

record StatisticDto (
        User user,
                     int totalTrainings,
                     double totalDistance,
                     int totalCaloriesBurned) {
}
