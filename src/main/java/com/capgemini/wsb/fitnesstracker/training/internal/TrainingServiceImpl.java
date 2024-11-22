package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;

    public List<Training> getAllTrainings() {
        return trainingRepository.getAllTrainings();

    }

    public List<Training> getAllTrainingsUser(Long idUser){
        return trainingRepository.getAllTrainingsUser(idUser);
    }

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    public List<Training> getAllTrainingsAfterEndDate(Date date) {
        return trainingRepository.getAllTrainingAfterEndDate(date);
    }

    public List<Training> getAllTrainingsForActivity(String activity) {
        return trainingRepository.getAllTraninigsForActivity(activity);
    }

    public Training addTraning(final Training training) {
        log.info("Creating Training {}", training);
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training has already DB ID, update is not permitted!");
        }

        return trainingRepository.save(training);
    }

    public List<Training> updateTraining(Long id, double distance) {
        return trainingRepository.updateTraining(id,distance);
    }
}
