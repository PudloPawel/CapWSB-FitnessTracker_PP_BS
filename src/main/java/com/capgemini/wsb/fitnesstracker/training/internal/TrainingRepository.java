package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;


interface TrainingRepository extends JpaRepository<Training, Long> {

    default List<Training> getAllTrainings() {
        return findAll()
                .stream()
                .toList();
    }

    default List<Training> getAllTrainingsUser(Long idUser){
        return findAll()
                .stream()
                .filter(
                        training1 -> Objects.equals(
                                training1.getUser().getId(),idUser))
                .toList();
    }

}
