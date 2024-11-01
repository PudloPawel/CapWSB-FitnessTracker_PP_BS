package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

/**
 * Mapper odpowiedzialny za konwersję obiektów {@link User} na różne reprezentacje DTO.
 * Używany wewnętrznie w celu ograniczenia ujawniania danych lub przygotowania
 * odpowiednich formatów dla API.
 */
@Component
class UserMapper {

    /**
     * Mapuje obiekt {@link User} na {@link UserDto}, zawierający pełne informacje o użytkowniku.
     *
     * @param user obiekt użytkownika do przekształcenia
     * @return obiekt {@link UserDto} z pełnymi informacjami o użytkowniku
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Mapuje obiekt {@link User} na uproszczony {@link UserDto2}, zawierający tylko ID, imię i nazwisko użytkownika.
     *
     * @param user obiekt użytkownika do przekształcenia
     * @return obiekt {@link UserDto2} z ID, imieniem i nazwiskiem użytkownika
     */
    UserDto2 toDto2(User user) {
        return new UserDto2(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    /**
     * Mapuje obiekt {@link User} na {@link UserEmailDto}, zawierający tylko ID i e-mail użytkownika.
     * Może być używane do wyszukiwania lub prezentowania minimalnych danych kontaktowych.
     *
     * @param user obiekt użytkownika do przekształcenia
     * @return obiekt {@link UserEmailDto} zawierający ID i e-mail użytkownika
     */
    UserEmailDto toDtoEmail(User user) {
        return new UserEmailDto(
                user.getId(),
                user.getEmail());
    }

}
