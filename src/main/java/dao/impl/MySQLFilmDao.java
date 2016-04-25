package dao.impl;

import dao.DirectorDao;
import dao.FilmDao;
import dao.StorageException;
import entity.Director;
import entity.Film;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.time.Duration;
import java.time.Year;
import java.util.Collections;
import java.util.List;

/**
 * Created on 22-Apr-16.
 *
 * @author Nazar Dub
 */
@Stateless(name = "MySQLFilmDao")
public class MySQLFilmDao implements FilmDao {

    @EJB(lookup = "java:global/jdbc/MySql/filmStudio")
    private DataSource dataSource;

    @EJB(beanName = "MySQLDirectorDao")
    private DirectorDao directorDao;

    @Override
    public List<Film> findBy(Year year) {
        throw new UnsupportedOperationException("This method hasn't implemented, yet");
    }

    @Override
    public List<Film> findBy(long directorId) {
        throw new UnsupportedOperationException("This method hasn't implemented, yet");
    }

    @Override
    public Film find(long id) throws StorageException {
        try (Connection connection = dataSource.getConnection()) {
            Film film = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, title, duration, year, description, director_id FROM films WHERE id = (?)");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                film = new Film(rs.getLong("id"));
                film.setTitle(rs.getString("title"));
                film.setDuration(Duration.ofSeconds(rs.getInt("duration")));
                film.setYear(Year.of(rs.getInt("year")));
                film.setDescription(rs.getString("description"));
                long directorId = rs.getLong("director_id");
                film.setDirector(directorDao.find(directorId));
            }
            return film;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Film create(Film entity) throws StorageException {
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO films VALUES (?,?,?,?,?,?)");
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getTitle());
            statement.setLong(3, entity.getDuration().getSeconds());
            statement.setLong(4, entity.getYear().getValue());
            statement.setString(5, entity.getDescription());
            statement.setLong(6, entity.getDirector().getId());
            statement.execute();

            return this.find(entity.getId());
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Film update(long id, Film entity) throws StorageException {
        throw new UnsupportedOperationException("This method hasn't implemented, yet");
    }

    @Override
    public void delete(long id) throws StorageException {
        throw new UnsupportedOperationException("This method hasn't implemented, yet");
    }
}
