package dao.conf;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import dao.impl.mongodb.DirectorCodec;
import dao.impl.mongodb.FilmCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created on 09-May-16.
 *
 * @author Nazar Dub
 */
@Stateless
public class MongoDBDataSourceImpl implements MongoDBDataSource {

    private static final String CONFIG_FILE_PATH = "mongodb-config.properties";

    private MongoDatabase database;

    @PostConstruct
    public void configureDataSource() {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE_PATH);
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String host = properties.getProperty("host");
        int port = Integer.valueOf(properties.getProperty("port"));
        String collectionName = properties.getProperty("collection");
        MongoClient mongoClient = new MongoClient(host, port);
        database = mongoClient.getDatabase(collectionName);

        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromCodecs(new DirectorCodec(), new FilmCodec()),
                MongoClient.getDefaultCodecRegistry());


        database = database.withCodecRegistry(codecRegistry);
    }

    @Override
    public MongoDatabase getDatabase() {
        return database;
    }
}
