package conf;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;

/**
 * Created on 12-Apr-16.
 *
 * @author Nazar Dub
 */
@DataSourceDefinition(
        className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
//        className = "com.mysql.jdbc.jdbc2.optional.MysqlXADataSource",
//        className = "com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource",
//        className = "com.mysql.jdbc.Driver",
        databaseName = "film_studio_test",
        serverName="localhost",
        portNumber = 3306,
        user = "root",
        password = "root",
        name = "java:global/jdbc/MySql/filmStudio",
        maxPoolSize = 30
)
@Stateless
public class DatabaseConf {
}
