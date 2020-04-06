package service;

import service.models.UserServiceModel;

public interface UserService {
    void saveUser(UserServiceModel user);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);
}
