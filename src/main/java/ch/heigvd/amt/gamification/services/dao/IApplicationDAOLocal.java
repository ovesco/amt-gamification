package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.Model.entity.Application;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IApplicationDAOLocal extends IGenericDAO<Application, Long> {

    public List<Application> findDeveloperApps(Long id, Integer page, Integer amount);

    public Long countDeveloperApps(Long id);
}
