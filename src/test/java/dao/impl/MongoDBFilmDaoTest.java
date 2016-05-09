package dao.impl;

import conf.InMemoryMongoDb;
import conf.InMemoryMongoDbImpl;
import dao.FilmDao;
import dao.conf.MongoDBDataSourceImpl;
import entity.Director;
import entity.Film;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import java.util.Collections;

/**
 * Created on 09-May-16.
 *
 * @author Nazar Dub
 */
public class MongoDBFilmDaoTest {

    private FilmDao filmDao;

    private InMemoryMongoDb inMemoryMongoDb;

    @Before
    public void setUp() throws Exception {
        inMemoryMongoDb = new InMemoryMongoDbImpl();
        inMemoryMongoDb.start();
        MongoDBDataSourceImpl dataSource = new MongoDBDataSourceImpl();
        dataSource.configureDataSource();
        filmDao = new MongoDBFilmDao(dataSource);
    }

    @After
    public void tearDown() throws Exception {
        inMemoryMongoDb.stop();
    }

    @Test
    public void testFindByYear() throws Exception {
        Film film = new Film(10);
        film.setTitle("Mad Max");
        film.setDuration(Duration.ofSeconds(7200));
        film.setYear(Year.of(2015));
        film.setDescription("Very interesting film");

        filmDao.create(film);
        Assert.assertArrayEquals(Collections.singletonList(film).toArray(), filmDao.findBy(Year.of(2015)).toArray());
    }

    @Test
    public void testFindByDirectorId() throws Exception {
        Director director = new Director(15);
        director.setFirstName("Ivan");
        director.setLastName("Ivanov");
        director.setBirthDate(LocalDate.of(1976, 3, 16));

        Film film = new Film(10);
        film.setTitle("Mad Max");
        film.setDuration(Duration.ofSeconds(7200));
        film.setYear(Year.of(2015));
        film.setDescription("Very interesting film");
        film.setDirector(director);

        filmDao.create(film);

        film.setDirector(null);
        Assert.assertArrayEquals(Collections.singletonList(film).toArray(), filmDao.findBy(15).toArray());
    }

    @Test
    public void testCreateAndFind() throws Exception {
        Film newFilm = new Film(10);
        newFilm.setTitle("Mad Max");
        newFilm.setDuration(Duration.ofSeconds(7200));
        newFilm.setYear(Year.of(2015));
        newFilm.setDescription("Very interesting film");

        filmDao.create(newFilm);
        Assert.assertEquals(newFilm, filmDao.find(10));
    }

    @Test
    public void testUpdate() throws Exception {
        Film film = new Film(10);
        film.setTitle("Mad Max");
        film.setDuration(Duration.ofSeconds(7200));
        film.setYear(Year.of(2015));
        film.setDescription("Very interesting film");

        filmDao.create(film);

        film.setTitle("Mad Max");
        filmDao.update(10, film);

        Assert.assertEquals(film, filmDao.find(10));
    }

    @Test
    public void testDelete() throws Exception {
        Film film = new Film(10);
        film.setTitle("Mad Max");
        film.setDuration(Duration.ofSeconds(7200));
        film.setYear(Year.of(2015));
        film.setDescription("Very interesting film");

        filmDao.create(film);
        filmDao.delete(10);
        Assert.assertNull(filmDao.find(10));
    }
}