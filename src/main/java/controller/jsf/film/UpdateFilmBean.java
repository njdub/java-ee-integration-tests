package controller.jsf.film;

import controller.jsf.NotifyMessageBean;
import dao.DirectorDao;
import dao.FilmDao;
import dao.StorageException;
import entity.Director;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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

    @EJB(beanName = "JPADirectorDao")
    private DirectorDao directorDao;

    @ManagedProperty("#{notifyMessageBean}")
    private NotifyMessageBean messageBean;


    private Map<String, String> directorsList;

    private String filmId;

    private String title;
    private String year;
    private String duration;
    private String directorId;
    private String description;

    @PostConstruct
    public void init() {
        try {
            directorsList = directorDao
                    .findAll()
                    .stream()
                    .collect(Collectors.toMap(Director::getFullName, d -> String.valueOf(d.getId())));
        } catch (StorageException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public void submit() {
        System.out.println(filmId);
        System.out.println(title);
        System.out.println(year);
        System.out.println(duration);
        System.out.println(directorId);
        System.out.println(description);

//        String[] durationPart = duration.split(":");
//        Duration resultDuration = Duration.ofHours(Long.valueOf(durationPart[0]))
//                .plus(Duration.ofMinutes(Long.valueOf(durationPart[1])));
//
//        Film film = new Film();
//
//        film.setTitle(title);
//        film.setDescription(description);
//        film.setYear(Year.parse(year));
//        film.setDuration(resultDuration);
//        film.setDirector(new Director(Long.valueOf(directorId)));
//
//
//        try {
//            filmDao.create(film);
//            messageBean.setMessage("Film was successful saved");
//            clearState();
//        } catch (StorageException e) {
//            messageBean.setMessage(e.getMessage());
//            e.printStackTrace();
//        }
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
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

    public Map<String, String> getDirectorsList() {
        return directorsList;
    }

    public void setDirectorsList(Map<String, String> directorsList) {
        this.directorsList = directorsList;
    }

    private void clearState() {
        title = "";
        year = "";
        duration = "";
        directorId = "";
        description = "";
    }
}
