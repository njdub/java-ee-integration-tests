package dao.impl;

import dao.DirectorDao;
import entity.Director;
import org.dbunit.Assertion;
import org.dbunit.DefaultDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.sql.DataSource;
import java.io.File;
import java.time.LocalDate;

/**
 * Created on 03-Apr-16.
 *
 * @author Nazar Dub
 */
@RunWith(Arquillian.class)
public class MySQLDirectorDaoIT {

    private IDatabaseTester tester;

    private IDatabaseConnection connection;

    @EJB(beanName = "MySQLDirectorDao")
    private DirectorDao directorDao;

    @EJB(lookup = "java:/MysqlNoXA/filmStudioTest")
    private DataSource dataSource;

    @Deployment
    public static Archive<WebArchive> createDeployment() throws Exception {
        File[] dbunit = Maven.resolver().resolve("org.dbunit:dbunit:2.5.1").withTransitivity().asFile();

        WebArchive war = ShrinkWrap
                .create(EmbeddedGradleImporter.class)
                .forThisProjectDirectory()
                .importBuildOutput()
                .as(WebArchive.class)
//                .deleteClass(DatabaseConf.class)
                .addAsLibraries(dbunit);
        File dir = new File("src/test/resources");
        addFiles(war, dir);
//        System.out.println(war.toString(true));
        return war;
    }

    private static void addFiles(WebArchive war, File dir) throws Exception {
        if (!dir.isDirectory()) {
            throw new Exception("Not a directory");
        }
        for (File f : dir.listFiles()) {
            if (f.isFile()) {
                war.addAsWebResource(f, "WEB-INF/classes" + f.getPath().replace("\\", "/").substring("src/test/resources".length()));
            } else {
                addFiles(war, f);
            }
        }
    }


    @Before
    public void setUp() throws Exception {
        connection = new MySqlConnection(dataSource.getConnection(), "film_studio_test");
        tester = new DefaultDatabaseTester(connection);
        IDataSet emptyData = getDataSetByPath("data/director/director-empty.xml");
        tester.setDataSet(emptyData);
        tester.setSetUpOperation(DatabaseOperation.NONE);
        tester.onSetup();
    }

    @After
    public void tearDown() throws Exception {
        DatabaseOperation tearDownOperation = DatabaseOperation.CLOSE_CONNECTION(DatabaseOperation.DELETE_ALL);
        tester.setTearDownOperation(tearDownOperation);
        tester.onTearDown();
    }

    private IDataSet getDataSetByPath(String path) throws DataSetException {
        return new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(path));
    }


    @Test
    public void testSave() throws Exception {
        Director director = new Director();
        director.setFirstName("Ivan");
        director.setLastName("Ivanov");
        director.setBirthDate(LocalDate.of(1976, 3, 16));

        directorDao.create(director);

        IDataSet expectedData = getDataSetByPath("data/director/director-single.xml");
        IDataSet actualData = connection.createDataSet();

        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "directors", ignore);
    }

    @Test
    public void testDelete() throws Exception {
        DatabaseOperation.INSERT.execute(connection, getDataSetByPath("data/director/director-single.xml"));

        directorDao.delete(15);

        IDataSet expectedData = getDataSetByPath("data/director/director-empty.xml");
        IDataSet actualData = connection.createDataSet();

        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "directors", new String[]{});
    }

}
