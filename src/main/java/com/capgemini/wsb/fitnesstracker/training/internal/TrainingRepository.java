package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


interface TrainingRepository extends JpaRepository<Training, Long> {

    default List<Training> getAllTrainings() {
        return findAll()
                .stream()
                .toList();


    }

}
