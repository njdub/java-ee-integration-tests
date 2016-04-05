package dao.impl;

import dao.DirectorDao;
import entity.Director;
import org.dbunit.Assertion;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.time.LocalDate;

/**
 * Created on 03-Apr-16.
 *
 * @author Nazar Dub
 */
public class MySQLDirectorDaoTest {

    IDatabaseTester tester;

    DirectorDao directorDao;

    DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        tester = new DataSourceDatabaseTester(dataSource, "film-studio-test");
        IDataSet beforeData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("data/initial/director-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    @After
    public void tearDown() throws Exception {
        DatabaseOperation tearDownOperation = DatabaseOperation.DELETE_ALL;
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

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("data/director/data-save.xml"));


        IDataSet actualData = tester.getConnection().createDataSet();

        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "director", ignore);
    }

}
