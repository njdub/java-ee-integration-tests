package dao.impl;

import dao.DirectorDao;
import dao.StorageException;
import entity.Director;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created on 08-Apr-16.
 *
 * @author Nazar Dub
 */
@Stateless(name = "MySQLDirectorDao")
public class MySQLDirectorDao implements DirectorDao {

    @EJB(lookup = "java:global/jdbc/MySql/filmStudio")
    private DataSource dataSource;

    @Override
    public List<Director> findBy(String lastName) {
        throw new UnsupportedOperationException("This method hasn't implemented, yet");
    }

    @Override
    public Director find(long id) {
        throw new UnsupportedOperationException("This method hasn't implemented, yet");
    }

    @Override
    public Director create(Director entity) throws StorageException {
        try (Connection connection = dataSource.getConnection()) {
            Date birth = Date.valueOf(entity.getBirthDate());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO directors VALUES (?,?,?,?)");
            statement.setLong(1,entity.getId());
            statement.setString(2,entity.getLastName());
            statement.setString(3,entity.getFirstName());
            statement.setDate(4,birth);
            statement.execute();
            return entity;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Director update(long id, Director entity) {
        throw new UnsupportedOperationException("This method hasn't implemented, yet");
    }

    @Override
    public void delete(long id) throws StorageException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM directors WHERE id = ?");
            statement.setLong(1,id);
            statement.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
