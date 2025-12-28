package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.model.User;
import cat.itacademy.s04.t01.userapi.model.UserRequest;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @PostMapping
    public User createUser(@RequestBody UserRequest userRequest) {
        // 1. Generem un ID aleatori
        UUID id = UUID.randomUUID();

        // 2. Creem l'usuari complet barrejant l'ID nou i les dades rebudes
        User newUser = new User(id, userRequest.name(), userRequest.email());

        // 3. El guardem a la llista
        users.add(newUser);

        // 4. El retornem (Spring el convertirà a JSON automàticament)
        return newUser;
    }

}