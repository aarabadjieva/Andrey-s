package web.beans;

import javax.faces.context.FacesContext;
import java.io.IOException;

public class BaseBean {

    public void redirect(String url) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect(url);
    }
}
