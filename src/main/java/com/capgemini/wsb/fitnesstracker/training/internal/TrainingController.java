package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    /*
    INSERT INTO TRAININGS (user_id, start_time, end_time, activity_type, distance, average_speed)
VALUES (2, '2022-11-11 10:00:00', '2022-11-11 11:30:00', 0, 5.0, 10.0);
     */


    @GetMapping(value = "/getAllTraining")
    public List<TrainingDto> getAllTraining() {
        return trainingService.getAllTrainings().stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping(value = "/getAllTrainingUser/{idUser}")
    public List<TrainingDto> getAllTraining(@PathVariable("idUser") Long idUser) {
        return trainingService.getAllTrainingsUser(idUser).stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping(value = "/getAllTrainingAfterEndDate/{date}")
    public List<TrainingDto> getAllTraining(@PathVariable("date") Date date) {
        return trainingService.getAllTrainingsAfterEndDate(date).stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping(value = "/getAllTrainingForActivity/{activity}")
    public List<TrainingDto> getTrainingForActivity(@PathVariable("activity") String activity) {
        return trainingService.getAllTrainingsForActivity(activity).stream().map(trainingMapper::toDto).toList();
    }

    @PostMapping(value = "/addTraining")
    public Training addTraining(@RequestBody TrainingDto trainingDto ) {
        System.out.println("Training User: " + trainingDto.user() + " passed to the request");

        /*
              // Request POSTMAN http://localhost:8080/v2/addTraining
              SON FORMAT TO PAYLOAD JSON
              {
                "user": {
                "id": 1
               },
               "startTime": "2024-11-18T10:00:00Z",
               "endTime": "2024-11-18T11:00:00Z",
               "activityType": 0,
               "distance" : 10,
               "averageSpeed": 1
        }

         */
        return trainingService.addTraning(new Training(
                trainingDto.user(),
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed()));
    }

    @PostMapping(value = "/updateTraining")
    public List<TrainingDto> updateTraining(@RequestBody TrainingDistanceDto trainingDto ) {

        /*
              // Request POSTMAN http://localhost:8080/v2/addTraining
              SON FORMAT TO PAYLOAD JSON
                "id" : 1,
                "distance" : 15
             }

          }

         */
        return trainingService.updateTraining(trainingDto.id(), trainingDto.distance())
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}
