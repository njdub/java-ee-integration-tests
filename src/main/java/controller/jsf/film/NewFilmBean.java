package controller.jsf.film;

import controller.jsf.NotifyMessageBean;
import dao.DirectorDao;
import dao.FilmDao;
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
public class NewFilmBean {

    @EJB(beanName = "JPAFilmDao")
    private FilmDao filmDao;

    @ManagedProperty("#{notifyMessageBean}")
    private NotifyMessageBean messageBean;

    private String title;
    private String year;
    private String duration;
    private String directorId;
    private String description;


    public NotifyMessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(NotifyMessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public void submit() {
        Director director = new Director();
//        director.setFirstName(firstName);
//        director.setLastName(lastName);
//        director.setBirthDate(LocalDate.parse(birthDate));
//        try {
////            directorDao.create(director);
//            messageBean.setMessage("Director was successful saved");
//            clearState();
//        } catch (StorageException e) {
//            messageBean.setMessage(e.getMessage());
//            e.printStackTrace();
//        }
    }

    private void clearState() {

    }
}
