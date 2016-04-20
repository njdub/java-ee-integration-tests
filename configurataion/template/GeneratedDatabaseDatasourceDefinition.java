package conf;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;

@DataSourceDefinition(
        className = "${className}",
        databaseName = "${databaseName}",
        serverName = "${serverName}",
        portNumber = ${portNumber},
        user = "${user}",
        password = "${password}",
        name = "${name}",
        maxPoolSize = 30
)
@Stateless
public class GeneratedDatabaseDatasourceDefinition {
}

