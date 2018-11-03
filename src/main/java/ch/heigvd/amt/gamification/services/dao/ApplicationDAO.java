package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.Model.entity.Application;

import javax.ejb.Stateless;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

@Stateless
@NamedQueries({
    @NamedQuery(name = "Application.findByDeveloper", query = "Select a from Application a Where a.developer.id = :id"),
    @NamedQuery(name = "Application.countForDeveloper", query = "Select COUNT(a) from Application a Where a.developer.id = :id")
})
public class ApplicationDAO extends GenericDAO<Application, Long> implements IApplicationDAOLocal {

    @Override
    public List<Application> findDeveloperApps(Long id, Integer page, Integer amount) {
        return em.createNamedQuery("Application.findByDeveloper").setParameter("id", id).setFirstResult(page*amount)
                .setMaxResults(amount).getResultList();
    }

    @Override
    public Long countDeveloperApps(Long id) {
        return (Long)em.createNamedQuery("Application.findByDeveloper").setParameter("id", id).getSingleResult();
    }
}
