package controller.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created on 28-May-16.
 *
 * @author Nazar Dub
 */
@ManagedBean
@RequestScoped
public class NotifyMessageBean {
    private String message = "I";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
