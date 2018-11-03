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

        Map<String, String> fields = new HashMap<>();
        fields.put("email", email);

        List<Developer> result = findBy(fields);

        return result.size() == 1 ? result.get(0) : null;

    }
}
