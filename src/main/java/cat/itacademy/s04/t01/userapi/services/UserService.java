package cat.itacademy.s04.t01.userapi.services;

import cat.itacademy.s04.t01.userapi.model.User;
import cat.itacademy.s04.t01.userapi.model.UserRequest;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers(String name);
    User getUserById(UUID id);
    User createUser(UserRequest userRequest);
}
