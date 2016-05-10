package entity;

import javax.persistence.*;
import java.time.Duration;
import java.time.Year;

/**
 * Created on 25-Mar-16.
 *
 * @author Nazar Dub
 */

@Entity
@Table(name = "films")
public class Film {

    @Id
    private long id;

    private String title;
    private Duration duration;
    private Year year;
    private String description;

    @JoinColumn(name = "director_id")
    @ManyToOne
    private Director director;

    public Film() {
    }

    public Film(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;
        if (id != film.id) return false;
        if (title != null ? !title.equals(film.title) : film.title != null) return false;
        if (duration != null ? !duration.equals(film.duration) : film.duration != null) return false;
        if (year != null ? !year.equals(film.year) : film.year != null) return false;
        if (description != null ? !description.equals(film.description) : film.description != null) return false;
        return director != null ? director.equals(film.director) : film.director == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", director=" + director +
                '}';
    }
}
