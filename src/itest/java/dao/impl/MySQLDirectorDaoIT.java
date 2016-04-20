package dao.impl;

import dao.DirectorDao;
import entity.Director;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JndiDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.time.LocalDate;

import static utils.DbUnitUtils.getDataSetByPath;

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

    @Deployment
    public static Archive<WebArchive> createDeployment() throws Exception {
        return ShrinkWrap
                .create(EmbeddedGradleImporter.class)
                .forThisProjectDirectory().forTasks("arquillianBuild")
                .importBuildOutput()
                .as(WebArchive.class);
    }

    @Before
    public void setUp() throws Exception {
        tester = new JndiDatabaseTester("java:/MysqlNoXA/filmStudioTest");
        connection = tester.getConnection();
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
