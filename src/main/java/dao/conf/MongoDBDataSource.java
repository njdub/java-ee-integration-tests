package dao.conf;

import com.mongodb.client.MongoDatabase;

/**
 * Created on 09-May-16.
 *
 * @author Nazar Dub
 */
public interface MongoDBDataSource {
    public MongoDatabase getDatabase();
}
