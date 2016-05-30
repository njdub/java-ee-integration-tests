package controller.jsf.director;

import controller.jsf.NotifyMessageBean;
import dao.DirectorDao;
import dao.StorageException;
import entity.Director;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.time.LocalDate;

/**
 * Created on 28-May-16.
 *
 * @author Nazar Dub
 */
@ManagedBean
@RequestScoped
public class NewDirectorBean {

    @EJB(beanName = "JPADirectorDao")
    private DirectorDao directorDao;

    @ManagedProperty("#{notifyMessageBean}")
    private NotifyMessageBean messageBean;

    private String firstName;
    private String lastName;
    private String birthDate;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public NotifyMessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(NotifyMessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public void submit() {
        Director director = new Director();
        director.setFirstName(firstName);
        director.setLastName(lastName);
        director.setBirthDate(LocalDate.parse(birthDate));
        try {
            directorDao.create(director);
            messageBean.setMessage("Director was successful saved");
            clearState();
        } catch (StorageException e) {
            messageBean.setMessage(e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearState() {
        firstName = "";
        lastName = "";
        birthDate = "";
    }
}
