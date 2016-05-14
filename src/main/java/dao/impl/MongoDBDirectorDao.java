package dao.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import dao.DirectorDao;
import dao.StorageException;
import dao.conf.MongoDBDataSource;
import entity.Director;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created on 09-May-16.
 *
 * @author Nazar Dub
 */
@Stateless(name = "MongoDBDirectorDao")
public class MongoDBDirectorDao implements DirectorDao {

    private static final String COLLECTION_DIRECTOR = "directors";

    @EJB
    private MongoDBDataSource dataSource;

    public MongoDBDirectorDao() {
    }

    public MongoDBDirectorDao(MongoDBDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private MongoCollection<Director> getCollection() {
        return dataSource.getDatabase().getCollection(COLLECTION_DIRECTOR, Director.class);
    }

    @Override
    public List<Director> findBy(String lastName) throws StorageException {
        MongoCollection<Director> collection = getCollection();
        List<Director> directors = new ArrayList<>();
        FindIterable<Director> results = collection.find(eq("last_name", lastName));
        Iterator<Director> iterator = results.iterator();
        if (iterator.hasNext())
            directors.add(iterator.next());
        return directors;
    }

    @Override
    public List<Director> findAll() throws StorageException {
        throw new UnsupportedOperationException("This method hasn't implemented, yet");
    }

    @Override
    public Director find(long id) throws StorageException {
        MongoCollection<Director> collection = getCollection();
        Director director = null;
        FindIterable<Director> results = collection.find(eq("id", id));
        Iterator<Director> iterator = results.iterator();
        if (iterator.hasNext())
            director = iterator.next();
        return director;
    }

    @Override
    public Director create(Director entity) throws StorageException {
        MongoCollection<Director> collection = getCollection();
        collection.insertOne(entity);
        return entity;
    }

    @Override
    public Director update(long id, Director entity) throws StorageException {
        MongoCollection<Director> collection = getCollection();
        collection.replaceOne(eq("id", id), entity);
        return find(entity.getId());
    }

    @Override
    public void delete(long id) throws StorageException {
        MongoCollection<Director> collection = getCollection();
        collection.deleteOne(eq("id", id));
    }
}
