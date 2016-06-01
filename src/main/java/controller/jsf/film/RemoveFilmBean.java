package controller.jsf.film;

import controller.jsf.NotifyMessageBean;
import dao.DirectorDao;
import dao.FilmDao;
import dao.StorageException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Created on 30-May-16.
 *
 * @author Nazar Dub
 */
@ManagedBean
@RequestScoped
public class RemoveFilmBean {

    @EJB(beanName = "JPAFilmDao")
    private FilmDao filmDao;

    @ManagedProperty("#{notifyMessageBean}")
    private NotifyMessageBean messageBean;

    @ManagedProperty(value = "#{param.filmId}")
    private Long filmId;

    public void removeFilm() {
        try {
            filmDao.delete(filmId);
            messageBean.setMessage("Film was successful deleted");
        } catch (StorageException e) {
            messageBean.setMessage(e.getMessage());
            e.printStackTrace();
        }
    }

    public FilmDao getFilmDao() {
        return filmDao;
    }

    public void setFilmDao(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    public NotifyMessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(NotifyMessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }
}
