package dao;

import entity.Director;

/**
 * Created on 25-Mar-16.
 *
 * @author Nazar Dub
 */
public interface GenericDao<T> {
    public T find(long id);

    public T create(T entity) throws StorageException;

    public T update(long id, T entity);

    public void delete(long id) throws StorageException;
}
