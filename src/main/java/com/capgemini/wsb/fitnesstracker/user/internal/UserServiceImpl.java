package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementacja serwisu {@link UserService} i providera {@link UserProvider} dla operacji
 * zarządzania użytkownikami, takich jak tworzenie, usuwanie, wyszukiwanie oraz aktualizacja użytkowników.
 */
@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * Tworzy nowego użytkownika w systemie. Jeśli użytkownik ma już przypisane ID, rzuca wyjątek.
     *
     * @param user obiekt {@link User} do zapisania
     * @return zapisany użytkownik
     * @throws IllegalArgumentException jeśli użytkownik ma już przypisane ID
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }

        return userRepository.save(user);
    }

    /**
     * Usuwa użytkownika na podstawie jego ID.
     *
     * @param id identyfikator użytkownika do usunięcia
     * @return komunikat potwierdzający usunięcie użytkownika
     */
    @Override
    public String deleteUser(Long id) {
        return userRepository.delateUser(id);
    }

    /**
     * Pobiera użytkownika na podstawie jego ID.
     *
     * @param userId identyfikator użytkownika do pobrania
     * @return {@link Optional} zawierający użytkownika lub {@link Optional#empty()}, jeśli użytkownik nie istnieje
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Pobiera użytkownika na podstawie jego adresu e-mail.
     *
     * @param email adres e-mail użytkownika do wyszukania
     * @return {@link Optional} zawierający użytkownika lub {@link Optional#empty()}, jeśli użytkownik nie istnieje
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Zwraca listę wszystkich użytkowników zapisanych w systemie.
     *
     * @return lista wszystkich użytkowników
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Wyszukuje użytkowników starszych niż określony wiek.
     *
     * @param age minimalny wiek użytkowników, których chcemy znaleźć
     * @return lista użytkowników starszych niż podany wiek
     */
    @Override
    public List<User> findAllUserByAge(Long age) {
        return userRepository.findAllUserByAge(age);
    }

    /**
     * Aktualizuje adres e-mail użytkownika o podanym identyfikatorze.
     *
     * @param userId identyfikator użytkownika, którego adres e-mail ma być zaktualizowany
     * @param email  nowy adres e-mail użytkownika
     * @return lista zawierająca użytkownika z zaktualizowanym adresem e-mail, jeśli operacja zakończyła się sukcesem
     */
    @Override
    public List<User> updateUser(Long userId, String email) {
        return userRepository.updateUserEmail(userId, email);
    }
}
