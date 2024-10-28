package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }

        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        log.info("Delete User {}", id);
        if (null == user.getId()) {
            throw new IllegalArgumentException("User don't exist DB ID, update is not permitted!");
        }else{
            userRepository.deleteById(id);
            return String.format("User %s deleted", id);
        }
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllUserByAge(Long age) {
        return userRepository.findAllUserByAge(age);
    }

    @Override
    public List<User> updateUser(Long userId, String email){

        return userRepository.updateUserEmail(userId, email);

    }


}
