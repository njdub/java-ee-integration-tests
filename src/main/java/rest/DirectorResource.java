package rest;

import dao.DirectorDao;
import dao.StorageException;
import entity.Director;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collection;

/**
 * Created on 16-May-16.
 *
 * @author Nazar Dub
 */

@Path("/director")
@Consumes({"application/json"})
@Produces({"application/json"})
public class DirectorResource {

    @EJB(beanName = "JPADirectorDao")
    private DirectorDao directorDao;

    @GET
    @Path("/all")
    public Collection<Director> getDirectors() throws StorageException {
        return directorDao.findAll();
    }

}
