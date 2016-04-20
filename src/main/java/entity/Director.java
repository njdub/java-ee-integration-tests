package entity;

import java.time.LocalDate;
import java.util.List;

/**
 * Created on 25-Mar-16.
 *
 * @author Nazar Dub
 */
public class Director {
    private long id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<Film> films;


    public Director() {
    }

    public Director(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Director director = (Director) o;

        if (id != director.id) return false;
        if (firstName != null ? !firstName.equals(director.firstName) : director.firstName != null) return false;
        if (lastName != null ? !lastName.equals(director.lastName) : director.lastName != null) return false;
        if (birthDate != null ? !birthDate.equals(director.birthDate) : director.birthDate != null) return false;
        return films != null ? films.equals(director.films) : director.films == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (films != null ? films.hashCode() : 0);
        return result;
    }
}
