package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import java.time.LocalDate;

/**
 * Reprezentuje dane użytkownika, w tym imię, nazwisko, datę urodzenia i adres e-mail.
 */
record UserDto(
        @Nullable Long Id,
        String firstName,
        String lastName,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        String email) {
}

/**
 * Reprezentuje dane użytkownika zawierające identyfikator, imię i nazwisko.
 */
record UserDto2(
        @Nullable Long Id,
        String firstName,
        String lastName) {
}

/**
 * Reprezentuje dane użytkownika, w tym identyfikator i adres e-mail.
 */
record UserEmailDto(
        @Nullable Long id,
        String email) {
}
