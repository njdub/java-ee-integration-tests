package dao.impl;

import dao.DirectorDao;
import dao.StorageException;
import entity.Director;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public List<Director> findBy(String lastName) throws StorageException {
        Objects.requireNonNull(lastName);
        try (Connection connection = dataSource.getConnection()) {
            List<Director> result = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id,first_name,last_name,birth_date FROM directors WHERE last_name = (?)");
            statement.setString(1, lastName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Director director = new Director(rs.getLong("id"));
                director.setFirstName(rs.getString("first_name"));
                director.setLastName(rs.getString("last_name"));
                director.setBirthDate(rs.getDate("birth_date").toLocalDate());
                result.add(director);
            }
            return result;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public List<Director> findAll() throws StorageException {
        throw new UnsupportedOperationException("This method hasn't implemented, yet");
    }

    @Override
    public Director find(long id) throws StorageException {
        try (Connection connection = dataSource.getConnection()) {
            Director director = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id,first_name,last_name,birth_date FROM directors WHERE id = (?)");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                director = new Director(rs.getLong("id"));
                director.setFirstName(rs.getString("first_name"));
                director.setLastName(rs.getString("last_name"));
                director.setBirthDate(rs.getDate("birth_date").toLocalDate());
            }
            return director;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Director create(Director entity) throws StorageException {
        try (Connection connection = dataSource.getConnection()) {
            Date birth = Date.valueOf(entity.getBirthDate());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO directors VALUES (?,?,?,?)");
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getFirstName());
            statement.setDate(4, birth);
            statement.execute();
            return entity;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Director update(long id, Director entity) throws StorageException {
        try (Connection connection = dataSource.getConnection()) {
            Date birth = Date.valueOf(entity.getBirthDate());
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE directors SET last_name = (?), first_name = (?), birth_date = (?) WHERE id = (?)");
            statement.setLong(4, id);
            statement.setString(1, entity.getLastName());
            statement.setString(2, entity.getFirstName());
            statement.setDate(3, birth);
            statement.execute();
            return entity;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(long id) throws StorageException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM directors WHERE id = ?");
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
