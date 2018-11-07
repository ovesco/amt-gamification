package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.Model.entity.BaseEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface IGenericDAO<T extends BaseEntity, PK> {

    public PK create(T t);

    public void update(T t) throws EntityNotFoundException;

    public void delete(T t) throws EntityNotFoundException;

    public long count();

    public T find(PK id) throws EntityNotFoundException;

    public List<T> findAll();

    public List<T> paginate(Integer page, Integer amount, String sortField, String sortDirection);
}
