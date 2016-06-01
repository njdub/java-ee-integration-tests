package controller.jsf.film;

import controller.jsf.NotifyMessageBean;
import dao.DirectorDao;
import dao.FilmDao;
import dao.StorageException;
import entity.Director;
import entity.Film;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.time.Duration;
import java.time.Year;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 28-May-16.
 *
 * @author Nazar Dub
 */
@ManagedBean
@RequestScoped
public class UpdateFilmBean {

    @EJB(beanName = "JPAFilmDao")
    private FilmDao filmDao;

    @ManagedProperty("#{notifyMessageBean}")
    private NotifyMessageBean messageBean;


    private Long filmId;

    private String title;
    private String year;
    private String duration;
    private String directorId;
    private String description;


    public void submit() {
        try {
            String[] durationPart = duration.split(":");
            Duration resultDuration = Duration.ofHours(Long.valueOf(durationPart[0]))
                    .plus(Duration.ofMinutes(Long.valueOf(durationPart[1])));

            Film film = filmDao.find(filmId);
            film.setTitle(title);
            film.setDescription(description);
            film.setYear(Year.parse(year));
            film.setDuration(resultDuration);
            film.setDirector(new Director(Long.valueOf(directorId)));

            filmDao.update(filmId, film);
            messageBean.setMessage("Film was successful updated");
        } catch (StorageException e) {
            messageBean.setMessage(e.getMessage());
            e.printStackTrace();
        }
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDirectorId() {
        return directorId;
    }

    public void setDirectorId(String directorId) {
        this.directorId = directorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NotifyMessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(NotifyMessageBean messageBean) {
        this.messageBean = messageBean;
    }

}
