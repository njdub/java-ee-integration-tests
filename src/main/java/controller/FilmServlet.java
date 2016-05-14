package controller;

import dao.FilmDao;
import dao.StorageException;
import entity.Film;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
