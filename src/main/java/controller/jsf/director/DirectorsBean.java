package controller.jsf.director;

import dao.DirectorDao;
import dao.StorageException;
import entity.Director;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created on 22-May-16.
 *
 * @author Nazar Dub
 */
@ManagedBean
@ApplicationScoped
public class DirectorsBean {

    @EJB(beanName = "JPADirectorDao")
    private DirectorDao directorDao;

    public List<Director> getDirectors() throws StorageException {
        return directorDao.findAll();
    }

}
