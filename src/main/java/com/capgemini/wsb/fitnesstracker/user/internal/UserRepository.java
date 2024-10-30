package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */

    default List<User> findAllUserIdAndName() {

        List<User> user = findAll();
        List<User> users = new ArrayList<User>();

        for(User u : user) {
         users.add(new User(u.getId(),u.getFirstName(),u.getLastName()));
        }
        return users;
    }

    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    default List<User> findAllUserByAge(Long age){

        return findAll()
                .stream()
                .filter(user -> {
                    LocalDate birthdate = user.getBirthdate();
                    if (birthdate == null) {
                        return false;
                    }
                    int userAge = Period.between(birthdate, LocalDate.now()).getYears();
                    return userAge > age;
                })
                .toList();
    }

    default List<User> updateUserEmail(Long id, String email) {

        User userToEdit = findById(id).orElse(null);
        if (userToEdit != null) {
            userToEdit.setEmail(email);
            save(userToEdit);
        }

        return findAll()
                .stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .toList();
    }

    default String delateUser(Long id){
        User user = findById(id).orElse(null);
        assert user != null;
        if (null == user.getId()) {
            throw new IllegalArgumentException("User don't exist DB ID, update is not permitted!");
        }else{
            deleteById(id);
            return String.format("User %s deleted", id);
        }
    }

}
