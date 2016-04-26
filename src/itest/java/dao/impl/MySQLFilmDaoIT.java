package dao.impl;

import dao.FilmDao;
import entity.Director;
import entity.Film;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static utils.DbUnitUtils.getDataSetByPath;

/**
 * Created on 22-Apr-16.
 *
 * @author Nazar Dub
 */

@RunWith(Arquillian.class)
public class MySQLFilmDaoIT {

    private IDatabaseTester tester;

    private IDatabaseConnection connection;

    @EJB(beanName = "MySQLFilmDao")
    private FilmDao filmDao;

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
        tester = new JndiDatabaseTester("java:global/jdbc/MySql/filmStudio");
        connection = tester.getConnection();
        IDataSet emptyData = getDataSetByPath("data/empty.xml");
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
    public void testFind() throws Exception {
        DatabaseOperation.INSERT.execute(connection, getDataSetByPath("data/group.xml"));

        Director director = new Director(15);
        director.setFirstName("Ivan");
        director.setLastName("Ivanov");
        director.setBirthDate(LocalDate.of(1976, 3, 16));

        Film expectedFilm = new Film(10);
        expectedFilm.setTitle("Mad Max");
        expectedFilm.setDuration(Duration.ofSeconds(7200));
        expectedFilm.setYear(Year.of(2015));
        expectedFilm.setDescription("Very interesting film");
        expectedFilm.setDirector(director);

        Film actualFilm = filmDao.find(10);

        Assert.assertEquals(expectedFilm, actualFilm);
    }

    @Test
    public void testSave() throws Exception {
        DatabaseOperation.INSERT.execute(connection, getDataSetByPath("data/director/director-single.xml"));

        Director director = new Director(15);
        director.setFirstName("Ivan");
        director.setLastName("Ivanov");
        director.setBirthDate(LocalDate.of(1976, 3, 16));

        Film newFilm = new Film(10);
        newFilm.setTitle("Mad Max");
        newFilm.setDuration(Duration.ofSeconds(7200));
        newFilm.setYear(Year.of(2015));
        newFilm.setDescription("Very interesting film");
        newFilm.setDirector(director);

        Film actualFilm = filmDao.create(newFilm);

        Assert.assertEquals(newFilm, actualFilm);

        IDataSet expectedData = getDataSetByPath("data/group.xml");
        IDataSet actualData = connection.createDataSet();

        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "films", new String[]{});
    }

    @Test
    public void testDelete() throws Exception {
        DatabaseOperation.INSERT.execute(connection, getDataSetByPath("data/group.xml"));

        filmDao.delete(10);

        IDataSet expectedData = getDataSetByPath("data/director/director-single.xml");
        IDataSet actualData = connection.createDataSet();

        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "films", new String[]{});
    }

    @Test
    public void testUpdate() throws Exception {
        DatabaseOperation.INSERT.execute(connection, getDataSetByPath("data/group.xml"));

        Director director = new Director(15);
        director.setFirstName("Ivan");
        director.setLastName("Ivanov");
        director.setBirthDate(LocalDate.of(1976, 3, 16));

        Film newFilm = new Film(10);
        newFilm.setTitle("Mad Max");
        newFilm.setDuration(Duration.ofSeconds(3600));
        newFilm.setYear(Year.of(2015));
        newFilm.setDescription("Not very interesting film");
        newFilm.setDirector(director);

        Film actualFilm = filmDao.update(10, newFilm);

        Assert.assertEquals(newFilm, actualFilm);

        IDataSet expectedData = getDataSetByPath("data/group-updated.xml");
        IDataSet actualData = connection.createDataSet();

        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "films", new String[]{});
    }

    @Test
    public void testFindByDirectorId() throws Exception {
        DatabaseOperation.INSERT.execute(connection, getDataSetByPath("data/film-big-group.xml"));

        Director director = new Director(15);
        director.setFirstName("Ivan");
        director.setLastName("Ivanov");
        director.setBirthDate(LocalDate.of(1976, 3, 16));

        Film filmOne = new Film(10);
        filmOne.setTitle("Mad Max");
        filmOne.setDuration(Duration.ofSeconds(7200));
        filmOne.setYear(Year.of(2015));
        filmOne.setDescription("Very interesting film");
        filmOne.setDirector(director);

        Film filmTwo = new Film(11);
        filmTwo.setTitle("Dead Max");
        filmTwo.setDuration(Duration.ofSeconds(3600));
        filmTwo.setYear(Year.of(2016));
        filmTwo.setDescription("Not very interesting film");
        filmTwo.setDirector(director);

        List<Film> expectedResult = Arrays.asList(filmOne, filmTwo);

        List<Film> actualResult = filmDao.findBy(director.getId());

        Assert.assertArrayEquals(expectedResult.toArray(), actualResult.toArray());
    }

    @Test
    public void testFindByYear() throws Exception {
        DatabaseOperation.INSERT.execute(connection, getDataSetByPath("data/film-big-group.xml"));

        Director director = new Director(15);
        director.setFirstName("Ivan");
        director.setLastName("Ivanov");
        director.setBirthDate(LocalDate.of(1976, 3, 16));

        Film filmTwo = new Film(11);
        filmTwo.setTitle("Dead Max");
        filmTwo.setDuration(Duration.ofSeconds(3600));
        filmTwo.setYear(Year.of(2016));
        filmTwo.setDescription("Not very interesting film");
        filmTwo.setDirector(director);

        List<Film> expectedResult = Collections.singletonList(filmTwo);

        List<Film> actualResult = filmDao.findBy(Year.of(2016));

        Assert.assertArrayEquals(expectedResult.toArray(), actualResult.toArray());

    }
}
