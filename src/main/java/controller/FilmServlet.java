package controller;

import dao.FilmDao;
import dao.StorageException;
import entity.Director;
import entity.Film;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Year;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

/**
 * Created on 11-May-16.
 *
 * @author Nazar Dub
 */
@WebServlet(name = "FilmServlet", urlPatterns = "/films")
public class FilmServlet extends HttpServlet {

    @EJB(beanName = "JPAFilmDao")
    private FilmDao filmDao;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String year = request.getParameter("year");
        String duration = request.getParameter("duration");
        String directorId = request.getParameter("director_id");
        String description = request.getParameter("description");

        String[] durationPart = duration.split(":");
        Duration resultDuration = Duration.ofHours(Long.valueOf(durationPart[0]))
                .plus(Duration.ofMinutes(Long.valueOf(durationPart[1])));

        Film film = new Film();

        film.setTitle(title);
        film.setDescription(description);
        film.setYear(Year.parse(year));
        film.setDuration(resultDuration);
        film.setDirector(new Director(Long.valueOf(directorId)));


        try {
            filmDao.create(film);
            request.setAttribute("message", "Film was successful saved");
            doGet(request, response);
        } catch (StorageException e) {
            response.sendError(SC_INTERNAL_SERVER_ERROR, e.getMessage());
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Film> result = filmDao.findAll();
            request.setAttribute("films", result);
            request.getRequestDispatcher("WEB-INF/view/films.jsp").forward(request, response);
        } catch (StorageException e) {
            response.sendError(SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
