package data.repository;

import data.entity.User;

public interface UserRepository {
    void saveUser(User user);

    User findByUsernameAndPassword(String username, String password);
}
