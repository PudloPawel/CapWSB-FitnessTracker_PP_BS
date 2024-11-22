package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
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

    default List<Training> getAllTrainingAfterEndDate(Date date){
        return findAll()
                .stream()
                .filter(training -> {
                    return training.getEndTime().after(date);
                })
                .toList();
    }

    default List<Training> getAllTraninigsForActivity(String activity){
        return findAll()
                .stream()
                .filter(training ->
                        Objects.equals(
                                training.getActivityType(),activity)).toList();
    }

    default List<Training> updateTraining(Long id, double distance){
        Training trainingToEdit = findById(id).orElse(null);
        if (trainingToEdit != null) {
            trainingToEdit.setDistance(null);
            save(trainingToEdit);
        }

        return findAll()
                .stream()
                .filter(user -> {
                    assert trainingToEdit != null;
                    return Objects.equals(trainingToEdit.getId(), id);
                })
                .toList();
    };
}
