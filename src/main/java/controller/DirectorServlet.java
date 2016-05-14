package controller;

import dao.DirectorDao;
import dao.StorageException;
import entity.Director;

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
 * Created on 12-May-16.
 *
 * @author Nazar Dub
 */
@WebServlet(name = "DirectorServlet", urlPatterns = "/directors")
public class DirectorServlet extends HttpServlet {

    @EJB(beanName = "JPADirectorDao")
    private DirectorDao directorDao;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Director> result = directorDao.findAll();
            request.setAttribute("directors", result);
            request.getRequestDispatcher("WEB-INF/view/directors.jsp").forward(request, response);
        } catch (StorageException e) {
            response.sendError(SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
