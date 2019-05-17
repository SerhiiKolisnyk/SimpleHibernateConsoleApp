package kolisnyk.task.service;

import java.sql.SQLException;
import java.util.List;
import kolisnyk.task.dao.implementation.LectorDaoImpl;
import kolisnyk.task.entity.LectorEntity;

public class LectorService {

  public List<LectorEntity> findAll() throws SQLException {
    return new LectorDaoImpl().findAll();
  }

  public LectorEntity findById(Integer id) throws SQLException {
    return new LectorDaoImpl().findById(id);
  }

  public void create(LectorEntity entity) throws SQLException {
    new LectorDaoImpl().create(entity);
  }

  public void update(LectorEntity entity) throws SQLException {
    new LectorDaoImpl().update(entity);
  }

  public void delete(Integer id) throws SQLException {
    new LectorDaoImpl().delete(id);
  }

  public List<LectorEntity> findByTemplate(String template) throws SQLException {
    return new LectorDaoImpl().findByTemplate(template);
  }

}
