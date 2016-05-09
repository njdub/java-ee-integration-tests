package conf;

/**
 * Created on 09-May-16.
 *
 * @author Nazar Dub
 */
public interface InMemoryMongoDb {
    public void start() throws Exception;

    public void stop();
}
