package dao.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import dao.FilmDao;
import dao.StorageException;
import dao.conf.MongoDBDataSource;
import entity.Film;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created on 09-May-16.
 *
 * @author Nazar Dub
 */
@Stateless(name = "MongoDBFilmDao")
public class MongoDBFilmDao implements FilmDao {

    private static final String COLLECTION_FILM = "films";

    @EJB
    private MongoDBDataSource dataSource;

    public MongoDBFilmDao() {
    }

    public MongoDBFilmDao(MongoDBDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private MongoCollection<Film> getCollection() {
        return dataSource.getDatabase().getCollection(COLLECTION_FILM, Film.class);
    }

    @Override
    public List<Film> findBy(Year year) throws StorageException {
        MongoCollection<Film> collection = getCollection();

        List<Film> films = new ArrayList<>();
        FindIterable<Film> results = collection.find(eq("year", year.getValue()));
        Iterator<Film> iterator = results.iterator();
        if (iterator.hasNext())
            films.add(iterator.next());
        return films;
    }

    @Override
    public List<Film> findBy(long directorId) throws StorageException {
        MongoCollection<Film> collection = getCollection();

        List<Film> films = new ArrayList<>();
        FindIterable<Film> results = collection.find(eq("director", directorId));
        Iterator<Film> iterator = results.iterator();
        if (iterator.hasNext())
            films.add(iterator.next());
        return films;
    }

    @Override
    public List<Film> findAll() throws StorageException {
        throw new UnsupportedOperationException("This method hasn't implemented, yet");
    }

    @Override
    public Film find(long id) throws StorageException {
        MongoCollection<Film> collection = getCollection();
        Film film = null;
        FindIterable<Film> results = collection.find(eq("id", id));
        Iterator<Film> iterator = results.iterator();
        if (iterator.hasNext())
            film = iterator.next();
        return film;
    }

    @Override
    public Film create(Film entity) throws StorageException {
        MongoCollection<Film> collection = getCollection();
        collection.insertOne(entity);
        return entity;
    }

    @Override
    public Film update(long id, Film entity) throws StorageException {
        MongoCollection<Film> collection = getCollection();
        collection.replaceOne(eq("id", id), entity);
        return find(entity.getId());
    }

    @Override
    public void delete(long id) throws StorageException {
        MongoCollection<Film> collection = getCollection();
        collection.deleteOne(eq("id", id));
    }
}
