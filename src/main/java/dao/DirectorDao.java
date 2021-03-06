package dao;

import entity.Director;

import java.util.List;

/**
 * Created on 25-Mar-16.
 *
 * @author Nazar Dub
 */
public interface DirectorDao extends GenericDao<Director> {

    public List<Director> findBy(String lastName) throws StorageException;

    public List<Director> findAll() throws StorageException;
}
