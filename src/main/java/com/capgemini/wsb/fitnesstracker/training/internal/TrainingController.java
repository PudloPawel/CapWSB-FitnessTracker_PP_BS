package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontroler obsługujący operacje na treningach.
 * Umożliwia dodawanie, usuwanie oraz pobieranie treningów.
 */
@RestController
@RequestMapping("/v2")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;


    @GetMapping(value = "/getAllTraining")
    public List<TrainingDto> getAllTraining() {
        return trainingService.getAllTrainings().stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping(value = "/getAllTrainingUser/{idUser}")
    public List<TrainingDto> getAllTraining(@PathVariable("idUser") Long idUser) {
        return trainingService.getAllTrainingsUser(idUser).stream().map(trainingMapper::toDto).toList();
    }

}
