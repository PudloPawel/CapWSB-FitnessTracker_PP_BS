package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

/**
 * Mapper odpowiedzialny za konwersję obiektów {@link Training} na różne reprezentacje DTO.
 * Używany wewnętrznie w celu ograniczenia ujawniania danych lub przygotowania
 * odpowiednich formatów dla API.
 */
@Component
class TrainingMapper {

    /**
     * Mapuje obiekt {@link User} na {@link TrainingDto}, zawierający pełne informacje o treningach.
     *
     * @param training obiekt użytkownika do przekształcenia
     * @return obiekt {@link TrainingDto} z pełnymi informacjami o użytkowniku
     */
    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getId(),
                training.getUser()
                ,training.getStartTime()
                ,training.getEndTime()
                ,training.getActivityType()
                ,training.getDistance()
                ,training.getAverageSpeed());
    }


}
