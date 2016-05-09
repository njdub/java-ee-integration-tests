package dao.impl;

import conf.InMemoryMongoDb;
import conf.InMemoryMongoDbImpl;
import dao.DirectorDao;
import dao.conf.MongoDBDataSourceImpl;
import entity.Director;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;

/**
 * Created on 09-May-16.
 *
 * @author Nazar Dub
 */
public class MongoDBDirectorDaoTest {

    private DirectorDao directorDao;

    private InMemoryMongoDb inMemoryMongoDb;

    @Before
    public void setUp() throws Exception {
        inMemoryMongoDb = new InMemoryMongoDbImpl();
        inMemoryMongoDb.start();
        MongoDBDataSourceImpl dataSource = new MongoDBDataSourceImpl();
        dataSource.configureDataSource();
        directorDao = new MongoDBDirectorDao(dataSource);
    }

    @After
    public void tearDown() throws Exception {
        inMemoryMongoDb.stop();
    }

    @Test
    public void testFindByLastName() throws Exception {
        Director newDirector = new Director(16);
        newDirector.setFirstName("Oleg");
        newDirector.setLastName("Olegovich");
        newDirector.setBirthDate(LocalDate.of(1985, 2, 16));
        directorDao.create(newDirector);
        Assert.assertArrayEquals(Collections.singletonList(newDirector).toArray(), directorDao.findBy("Olegovich").toArray());
    }


    @Test
    public void testCreateAndFind() throws Exception {
        Director newDirector = new Director(16);
        newDirector.setFirstName("Oleg");
        newDirector.setLastName("Olegovich");
        newDirector.setBirthDate(LocalDate.of(1985, 2, 16));
        directorDao.create(newDirector);
        Assert.assertEquals(newDirector, directorDao.find(16));
    }


    @Test
    public void testUpdate() throws Exception {
        Director director = new Director(16);
        director.setFirstName("Oleg");
        director.setLastName("Olegovich");
        director.setBirthDate(LocalDate.of(1985, 2, 16));
        directorDao.create(director);
        director.setLastName("Vitalik");
        directorDao.update(16, director);
        Assert.assertEquals(director, directorDao.find(16));
    }

    @Test
    public void testDelete() throws Exception {
        Director newDirector = new Director(16);
        newDirector.setFirstName("Oleg");
        newDirector.setLastName("Olegovich");
        newDirector.setBirthDate(LocalDate.of(1985, 2, 16));
        directorDao.create(newDirector);
        directorDao.delete(16);
        Assert.assertNull(directorDao.find(16));
    }
}