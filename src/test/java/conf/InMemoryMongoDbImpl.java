package conf;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created on 09-May-16.
 *
 * @author Nazar Dub
 */
public class InMemoryMongoDbImpl implements InMemoryMongoDb {

    private MongodExecutable mongodExecutable;

    private MongodProcess mongod;

    IMongodConfig mongodConfig;

    private void configureMongod() throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("mongodb-config.properties");
        Properties properties = new Properties();
        properties.load(in);
        int port = Integer.valueOf(properties.getProperty("port"));
        mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()))
                .build();
    }

    @Override
    public void start() throws IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        configureMongod();
        mongodExecutable = starter.prepare(mongodConfig);
        mongod = mongodExecutable.start();
    }

    @Override
    public void stop() {
        mongodExecutable.stop();
    }
}
