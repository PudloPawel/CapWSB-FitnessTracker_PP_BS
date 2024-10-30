package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        // Request POSTMAN http://localhost:8080/v1
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        // Request POSTMAN http://localhost:8080/v1/addUser
        /* JSON FORMAT TO PAYLOAD JSON
            {
                "firstName" : "Adam",
                "lastName" : "Kawa",
                "birthdate" : "2000-01-02",
```                "email" : "kawa2000@wp.pl"
            }
         */
        return userService.createUser(new User(userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email()));
    }

    @DeleteMapping("/deleteUser/{idUser}")
    public String deleteUser(@PathVariable("idUser") Long idUser) {
        // Request POSTMAN http://localhost:8080/v1/deleteUser/1
        return userService.deleteUser(idUser);

    }

    @GetMapping(value = "/getUsers")
    public List<UserDto2> getAllUsersIdAndName () {
            // Request POSTMAN http://localhost:8080/v1/getUsers
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto2)
                .toList();
    }

    @GetMapping(value = "/getUser/{idUser}")
    public List<UserDto> getUserById(@PathVariable("idUser") Long idUser) {
                // Request POSTMAN http://localhost:8080/v1/getUser/1
        return userService.getUser(idUser)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping(value = "/getUserByEmail/{email}")
    public List<UserEmailDto> getUserByEmail(@PathVariable("email") String email) {
        //Request POSTMAN http://localhost:8080/v1/getUserByEmail/biko@wp.pl
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toDtoEmail)
                .toList();
    }

    @GetMapping(value = "/getUserByAge/{age}")
    public List<UserDto> getUserAge(@PathVariable("age") Long age) {
        // Request POSTMAN http://localhost:8080/v1/getUserByAge/22
        return userService.findAllUserByAge(age)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }


    @PutMapping(value = "/updateUser")
    public List<UserDto> updateUser(@RequestBody UserEmailDto userDto) {

        /*
            Request POSTMAN http://localhost:8080/v1/updateUser

            {
                "id" : 1,
                "email" : "kawa32@wp.pl"
            }

         */

        return userService.updateUser(userDto.id(),userDto.email())
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

}
