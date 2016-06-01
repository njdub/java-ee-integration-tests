package controller.jsf.director;

import dao.DirectorDao;
import dao.StorageException;
import entity.Director;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 22-May-16.
 *
 * @author Nazar Dub
 */
@ManagedBean
@ApplicationScoped
public class DirectorsListBean {

    @EJB(beanName = "JPADirectorDao")
    private DirectorDao directorDao;

    public Map<String, String> get() throws StorageException {
        return directorDao
                .findAll()
                .stream()
                .collect(Collectors.toMap(Director::getFullName, d -> String.valueOf(d.getId())));

    }

}
