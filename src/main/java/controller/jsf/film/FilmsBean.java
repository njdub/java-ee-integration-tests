package controller.jsf.film;

import dao.FilmDao;
import dao.StorageException;
import entity.Film;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created on 22-May-16.
 *
 * @author Nazar Dub
 */
@ManagedBean
@ApplicationScoped
public class FilmsBean {

    @EJB(beanName = "JPAFilmDao")
    private FilmDao filmDao;

    public List<Film> getFilms() throws StorageException {
        return filmDao.findAll();
    }

}
