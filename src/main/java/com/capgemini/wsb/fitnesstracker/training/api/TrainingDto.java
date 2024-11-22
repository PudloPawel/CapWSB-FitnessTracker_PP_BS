package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import org.eclipse.jdt.annotation.NonNullByDefault;

import java.util.Date;
import java.time.LocalDate;

public record TrainingDto(@Nullable Long Id,
                          User user,
                          Date startTime,
                          Date endTime,
                          ActivityType activityType,
                          double distance,
                          double averageSpeed) {

}


