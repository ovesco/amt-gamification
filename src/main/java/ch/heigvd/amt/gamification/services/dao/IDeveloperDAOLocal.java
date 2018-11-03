package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.Model.entity.Developer;

public interface IDeveloperDAOLocal extends IGenericDAO<Developer, Long> {

    public Developer findByEmail(String email);
}
