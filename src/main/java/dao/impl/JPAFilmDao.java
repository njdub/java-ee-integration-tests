package dao.impl;

import dao.FilmDao;
import dao.StorageException;
import entity.Film;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.Year;
import java.util.List;

/**
 * Created on 10-May-16.
 *
 * @author Nazar Dub
 */
@Stateless(name = "JPAFilmDao")
public class JPAFilmDao implements FilmDao {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;


    @Override
    public List<Film> findBy(Year year) throws StorageException {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Film> cq = criteriaBuilder.createQuery(Film.class);
        Root<Film> p = cq.from(Film.class);
        cq.where(criteriaBuilder.equal(p.get("year"), year.getValue()));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Film> findBy(long directorId) throws StorageException {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Film> cq = criteriaBuilder.createQuery(Film.class);
        Root<Film> p = cq.from(Film.class);
        cq.where(criteriaBuilder.equal(p.get("director"), directorId));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Film> findAll() throws StorageException {
        TypedQuery<Film> filmTypedQuery = em.createQuery("SELECT f FROM Film f", Film.class);
        return filmTypedQuery.getResultList();
    }

    @Override
    public Film find(long id) throws StorageException {
        return em.find(Film.class, id);
    }

    @Override
    public Film create(Film entity) throws StorageException {
        return em.merge(entity);
    }

    @Override
    public Film update(long id, Film entity) throws StorageException {
        em.remove(em.find(Film.class, id));
        return em.merge(entity);
    }

    @Override
    public void delete(long id) throws StorageException {
        em.remove(em.find(Film.class, id));
    }
}
