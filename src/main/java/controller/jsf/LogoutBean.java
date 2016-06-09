package controller.jsf;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created on 09-Jun-16.
 *
 * @author Nazar Dub
 */
@ManagedBean
@RequestScoped
public class LogoutBean {

    @ManagedProperty("#{notifyMessageBean}")
    private NotifyMessageBean messageBean;

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            messageBean.setMessage("Successful logout");
        } catch (ServletException e) {
            e.printStackTrace();
            messageBean.setMessage(e.getMessage());
        }
    }

    public NotifyMessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(NotifyMessageBean messageBean) {
        this.messageBean = messageBean;
    }
}
