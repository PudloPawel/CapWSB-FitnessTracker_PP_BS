package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Interfejs repozytorium użytkowników rozszerzający {@link JpaRepository},
 * zawierający dodatkowe metody dostosowane do operacji na użytkownikach.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Wyszukuje użytkownika na podstawie adresu e-mail.
     *
     * @param email adres e-mail użytkownika, którego szukamy
     * @return {@link Optional} zawierający znalezionego użytkownika lub {@link Optional#empty()}, jeśli użytkownik nie został znaleziony
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Pobiera listę wszystkich użytkowników z systemu zawierającą tylko ID, imię i nazwisko.
     *
     * @return lista {@link User} zawierająca obiekty z uzupełnionymi tylko ID, imieniem i nazwiskiem
     */
    default List<User> findAllUserIdAndName() {
        List<User> user = findAll();
        List<User> users = new ArrayList<>();
        for (User u : user) {
            users.add(new User(u.getId(), u.getFirstName(), u.getLastName()));
        }
        return users;
    }

    /**
     * Wyszukuje wszystkich użytkowników, którzy mają więcej niż podany wiek.
     *
     * @param age minimalny wiek użytkowników, których chcemy znaleźć
     * @return lista użytkowników starszych niż podany wiek
     */
    default List<User> findAllUserByAge(Long age) {
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

    /**
     * Aktualizuje adres e-mail użytkownika o podanym identyfikatorze.
     *
     * @param id    identyfikator użytkownika, którego adres e-mail ma być zaktualizowany
     * @param email nowy adres e-mail użytkownika
     * @return lista zawierająca użytkownika z zaktualizowanym adresem e-mail, jeśli operacja zakończyła się sukcesem
     */
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

    /**
     * Usuwa użytkownika o podanym identyfikatorze z bazy danych.
     *
     * @param id identyfikator użytkownika do usunięcia
     * @return komunikat potwierdzający usunięcie użytkownika z bazy danych
     * @throws IllegalArgumentException jeśli użytkownik o podanym identyfikatorze nie istnieje
     */
    default String delateUser(Long id) {
        User user = findById(id).orElse(null);
        assert user != null;
        if (user.getId() == null) {
            throw new IllegalArgumentException("User doesn't exist in DB, deletion is not permitted!");
        } else {
            deleteById(id);
            return String.format("User %s deleted", id);
        }
    }
}
