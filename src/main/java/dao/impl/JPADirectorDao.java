package dao.impl;

import dao.DirectorDao;
import dao.StorageException;
import entity.Director;
import entity.Film;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created on 10-May-16.
 *
 * @author Nazar Dub
 */
@Stateless(name = "JPADirectorDao")
public class JPADirectorDao implements DirectorDao {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    public List<Director> findBy(String lastName) throws StorageException {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Director> cq = criteriaBuilder.createQuery(Director.class);
        Root<Director> p = cq.from(Director.class);
        cq.where(criteriaBuilder.equal(p.get("lastName"), lastName));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Director> findAll() throws StorageException {
        TypedQuery<Director> directorTypedQuery = em.createQuery("SELECT d FROM Director d", Director.class);
        return directorTypedQuery.getResultList();
    }

    @Override
    public Director find(long id) throws StorageException {
        return em.find(Director.class, id);
    }

    @Override
    public Director create(Director entity) throws StorageException {
        return em.merge(entity);
    }

    @Override
    public Director update(long id, Director entity) throws StorageException {
//        em.remove(em.find(Director.class, id));
        return em.merge(entity);
    }

    @Override
    public void delete(long id) throws StorageException {
        em.remove(em.find(Director.class, id));
    }
}
