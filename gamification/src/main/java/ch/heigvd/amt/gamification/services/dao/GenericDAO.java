package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.Model.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDAO<T extends BaseEntity<PK>, PK extends Serializable> implements IGenericDAO<T, PK> {

    @PersistenceContext
    EntityManager em;

    private final Class<T> jpaEntityClass;

    public GenericDAO() {
        jpaEntityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public PK create(T t) {
        em.persist(t);
        em.flush();
        return t.getId();
    }

    @Override
    public void update(T t) throws EntityNotFoundException {
        find(t.getId());
        em.merge(t);
    }

    @Override
    public void delete(T t) throws EntityNotFoundException {
        if(!em.contains(t))
            t = find(t.getId());

        em.remove(t);
    }

    @Override
    public long count() {
        return (long)em.createQuery("Select count(t) from " + jpaEntityClass.getSimpleName() + " t").getSingleResult();
    }

    @Override
    public T find(PK id) throws EntityNotFoundException {
        T result = em.find(jpaEntityClass, id);
        if(result == null)
            throw new EntityNotFoundException(id, jpaEntityClass);
        return result;
    }

    @Override
    public List<T> findPaginate(Integer page, Integer amount) {
        return em.createQuery("Select t from " + jpaEntityClass.getSimpleName() + " t")
                .setMaxResults(amount).setFirstResult(amount*page).getResultList();
    }

    @Override
    public Long findAmount() {
        return (Long)em.createQuery("Select COUNT(t) from " + jpaEntityClass.getSimpleName() + " t").getSingleResult();
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("Select t from " + jpaEntityClass.getSimpleName() + " t").getResultList();
    }

    @Override
    public List<T> paginate(Integer page, Integer amount, String sortField, String sortDirection) {
        return em.createQuery("Select t from " + jpaEntityClass.getSimpleName() + " t order by " + sortField + sortDirection)
                .setMaxResults(amount).setFirstResult(page*amount).getResultList();
    }
}
