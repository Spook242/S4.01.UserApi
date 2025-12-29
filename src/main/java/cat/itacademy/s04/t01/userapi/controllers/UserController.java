package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.exceptions.UserNotFoundException;
import cat.itacademy.s04.t01.userapi.model.User;
import cat.itacademy.s04.t01.userapi.model.UserRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAllUsers(@RequestParam(required = false) String name) {
        if (name == null) {
            return users;
        }

        return users.stream()
                .filter(user -> user.name().toLowerCase().contains(name.toLowerCase()))
                .toList();

    }

    @PostMapping
    public User createUser(@RequestBody UserRequest userRequest) {

        UUID id = UUID.randomUUID();
        User newUser = new User(id, userRequest.name(), userRequest.email());
        users.add(newUser);
        return newUser;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return users.stream()
                .filter(user -> user.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
}