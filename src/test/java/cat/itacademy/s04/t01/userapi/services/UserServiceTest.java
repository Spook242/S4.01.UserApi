package cat.itacademy.s04.t01.userapi.services;

import cat.itacademy.s04.t01.userapi.exceptions.EmailAlreadyExistsException;
import cat.itacademy.s04.t01.userapi.exceptions.UserNotFoundException;
import cat.itacademy.s04.t01.userapi.model.User;
import cat.itacademy.s04.t01.userapi.model.UserRequest;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_throwsException_whenEmailExists() {

        UserRequest request = new UserRequest("Duplicated User", "repe@email.com");

        when(userRepository.existsByEmail(request.email())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(request));

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void createUser_savesUser_whenEmailIsUnique() {

        UserRequest request = new UserRequest("New User", "new@email.com");

        when(userRepository.existsByEmail(request.email())).thenReturn(false);

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        User result = userService.createUser(request);

        assertNotNull(result.id());
        assertEquals("new@email.com", result.email());
        verify(userRepository).save(any(User.class));
    }


    @Test
    void getAllUsers_callsRepository() {
        when(userRepository.findAll()).thenReturn(List.of());
        userService.getAllUsers(null);
        verify(userRepository).findAll();
    }

    @Test
    void getUserById_throws_whenNotFound() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(id));
    }

    @Test
    void getAllUsers_callsSearch_whenNameIsProvided() {
        String searchName = "Anna";
        when(userRepository.searchByName(searchName)).thenReturn(List.of());

        userService.getAllUsers(searchName);

        verify(userRepository).searchByName(searchName);

        verify(userRepository, never()).findAll();
    }

    @Test
    void getAllUsers_callsFindAll_whenNameIsBlank() {
        String blankName = "   ";
        when(userRepository.findAll()).thenReturn(List.of());

        userService.getAllUsers(blankName);

        verify(userRepository).findAll();
        verify(userRepository, never()).searchByName(any());
    }
}