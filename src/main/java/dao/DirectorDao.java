package dao;

import entity.Director;
import entity.Film;

import java.time.Year;
import java.util.List;

/**
 * Created on 25-Mar-16.
 *
 * @author Nazar Dub
 */
public interface DirectorDao extends GenericDao<Director> {

    public List<Director> findBy(String lastName);
}
