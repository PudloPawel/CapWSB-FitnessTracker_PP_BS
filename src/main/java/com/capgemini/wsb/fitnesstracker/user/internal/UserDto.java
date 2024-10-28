package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

record UserDto(@Nullable Long Id, String firstName, String lastName,
               @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
               String email) {

}
record UserDto2(@Nullable Long Id, String firstName, String lastName) {

}

record UserEmailDto(@Nullable Long id, String email) {

}
