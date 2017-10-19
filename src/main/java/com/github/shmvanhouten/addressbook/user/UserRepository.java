package com.github.shmvanhouten.addressbook.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getAllUsers();

    void addUser(String testUser);

    void deleteUser(int userId);

    Optional<User> getUserById(int userId);
}
