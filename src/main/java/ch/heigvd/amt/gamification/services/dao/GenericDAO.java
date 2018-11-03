package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.Model.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public class GenericDAO<T extends BaseEntity<PK>, PK> implements IGenericDAO<T, PK> {

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
    public List<T> findBy(Map<String, String> terms) {

        int i = 0;
        StringBuilder sql = new StringBuilder("Select t from " + jpaEntityClass.getSimpleName() + " t ");

        // Build sql
        for(Map.Entry<String, String> entry : terms.entrySet())
            sql.append(i > 0 ? "And " : "").append("Where ")
                    .append(entry.getKey()).append(" = ").append(":val").append(i++).append(" ");

        Query query = em.createQuery(sql.toString());

        // Set parameters
        i = 0;
        for(Map.Entry<String, String> entry : terms.entrySet())
            query.setParameter("val" + i++, entry.getValue());

        return query.getResultList();
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
