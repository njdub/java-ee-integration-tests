package controller.jsf.director;

import controller.jsf.NotifyMessageBean;
import dao.DirectorDao;
import dao.StorageException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Created on 30-May-16.
 *
 * @author Nazar Dub
 */
@ManagedBean
@RequestScoped
public class RemoveDirectorBean {

    @EJB(beanName = "JPADirectorDao")
    private DirectorDao directorDao;

    @ManagedProperty("#{notifyMessageBean}")
    private NotifyMessageBean messageBean;

    @ManagedProperty(value = "#{param.directorId}")
    private Long directorId;

    public void removeDirector() {
        try {
            directorDao.delete(directorId);
            messageBean.setMessage("Director was successful deleted");
        } catch (StorageException e) {
            messageBean.setMessage(e.getMessage());
            e.printStackTrace();
        }
    }

    public DirectorDao getDirectorDao() {
        return directorDao;
    }

    public void setDirectorDao(DirectorDao directorDao) {
        this.directorDao = directorDao;
    }

    public NotifyMessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(NotifyMessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }
}
