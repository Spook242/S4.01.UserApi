package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserRepositoryTest {

    private InMemoryUserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryUserRepository();
    }

    @Test
    void save_persistsUser() {
        User user = new User(UUID.randomUUID(), "Test", "test@email.com");
        repository.save(user);

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void findById_returnsUser_whenExists() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "Test", "test@email.com");
        repository.save(user);

        Optional<User> found = repository.findById(id);

        assertTrue(found.isPresent());
        assertEquals(id, found.get().id());
    }

    @Test
    void findById_returnsEmpty_whenNotExists() {
        Optional<User> found = repository.findById(UUID.randomUUID());
        assertTrue(found.isEmpty());
    }

    @Test
    void searchByName_returnsMatches() {
        repository.save(new User(UUID.randomUUID(), "Anna", "anna@anna.com"));
        repository.save(new User(UUID.randomUUID(), "Joan", "joan@joan.com"));

        List<User> results = repository.searchByName("Ann");

        assertEquals(1, results.size());
        assertEquals("Anna", results.getFirst().name());
    }
}
