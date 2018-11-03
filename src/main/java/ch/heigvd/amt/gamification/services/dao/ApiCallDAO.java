package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.Model.entity.ApiCall;

import javax.ejb.Stateless;

@Stateless
public class ApiCallDAO extends GenericDAO<ApiCall, Long> implements IApiCallDaoLocal {
}
