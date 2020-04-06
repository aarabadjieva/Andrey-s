package service;

import data.entity.User;
import data.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import service.models.UserServiceModel;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
private final ModelMapper mapper;
    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveUser(UserServiceModel user) {
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        userRepository.saveUser(mapper.map(user, User.class));
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        try{
            return mapper.map(userRepository.findByUsernameAndPassword(username, DigestUtils.sha256Hex(password)), UserServiceModel.class);
        }catch (Exception e) {
            return null;
        }
    }
}
