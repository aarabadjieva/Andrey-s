package web.beans;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import service.UserService;
import service.models.UserServiceModel;
import web.models.user.UserRegisterModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@NoArgsConstructor
@Named
@RequestScoped
public class RegisterBean extends BaseBean{

    private UserRegisterModel model;
    private UserService userService;
    private ModelMapper mapper;

    @Inject
    public RegisterBean(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostConstruct
    private void init(){
        model = new UserRegisterModel();
    }

    public UserRegisterModel getModel() {
        return model;
    }

    public void setModel(UserRegisterModel model) {
        this.model = model;
    }

    public void registerUser() throws IOException {
        if (!model.getPassword().equals(model.getConfirmPassword())){
            return;
        }
        try{
            userService.saveUser(mapper.map(model, UserServiceModel.class));
        }catch (Exception e){
            return;
        }
        redirect("/users/login");
    }
}
