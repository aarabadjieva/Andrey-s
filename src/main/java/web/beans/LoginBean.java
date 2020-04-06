package web.beans;

import lombok.NoArgsConstructor;
import service.UserService;
import service.models.UserServiceModel;
import web.models.user.UserLoginModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@NoArgsConstructor
@Named
@RequestScoped
public class LoginBean extends BaseBean{

    private UserLoginModel model;
    private UserService userService;

    @Inject
    public LoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void init(){
        model = new UserLoginModel();
    }

    public UserLoginModel getModel() {
        return model;
    }

    public void setModel(UserLoginModel model) {
        this.model = model;
    }

    public void login() throws IOException {
        UserServiceModel user = userService.findUserByUsernameAndPassword(model.getUsername(), model.getPassword());
        if (user==null){
            return;
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", user.getUsername());
        redirect("/home");
    }
}
