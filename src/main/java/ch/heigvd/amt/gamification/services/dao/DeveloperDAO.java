package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.Model.entity.Developer;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class DeveloperDAO extends GenericDAO<Developer, Long> implements IDeveloperDAOLocal {

    @Override
    public Developer findByEmail(String email) {
        List<Developer> result = em.createNamedQuery("Developer.findByEmail").setParameter("email", email).getResultList();
        return result.size() == 0 ? null : result.get(0);
    }
}
