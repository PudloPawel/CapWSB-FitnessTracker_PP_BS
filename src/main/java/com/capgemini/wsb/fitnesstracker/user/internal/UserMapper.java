package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    UserDto2 toDto2(User user) {
        return new UserDto2(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    UserEmailDto toDtoEmail(User user) {
        return new UserEmailDto(
                        user.getId(),
                        user.getEmail());
    }

}
