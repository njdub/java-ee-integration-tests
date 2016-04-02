package dao;

import entity.Film;

import java.time.Year;
import java.util.List;

/**
 * Created on 25-Mar-16.
 *
 * @author Nazar Dub
 */
public interface FilmDao extends GenericDao<Film> {

    public List<Film> findBy(Year year);

    public List<Film> findBy(long directorId);

}
