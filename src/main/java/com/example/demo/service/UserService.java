package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final Map<Long, User> userStorage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public UserService() {
        // Sample seed data
        saveSeed(new User(null, "Alice Johnson", "alice@example.com", "ADMIN"));
        saveSeed(new User(null, "Bob Smith", "bob@example.com", "USER"));
        saveSeed(new User(null, "Charlie Brown", "charlie@example.com", "USER"));
    }

    private void saveSeed(User user) {
        long id = idGenerator.getAndIncrement();
        user.setId(id);
        userStorage.put(id, user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userStorage.values());
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userStorage.get(id));
    }

    public User createUser(User user) {
        long id = idGenerator.getAndIncrement();
        user.setId(id);
        userStorage.put(id, user);
        return user;
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        if (!userStorage.containsKey(id)) {
            return Optional.empty();
        }
        updatedUser.setId(id);
        userStorage.put(id, updatedUser);
        return Optional.of(updatedUser);
    }

    public boolean deleteUser(Long id) {
        return userStorage.remove(id) != null;
    }
}
