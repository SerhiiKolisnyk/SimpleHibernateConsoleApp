package kolisnyk.task.service;

import java.sql.SQLException;
import java.util.List;
import kolisnyk.task.dao.implementation.DegreeDaoImpl;
import kolisnyk.task.entity.DegreeEntity;

public class DegreeService {
  public List<DegreeEntity> findAll() throws SQLException {
    return new DegreeDaoImpl().findAll();
  }

  public DegreeEntity findById(Integer id) throws SQLException {
    return new DegreeDaoImpl().findById(id);
  }

  public void create(DegreeEntity entity) throws SQLException {
    new DegreeDaoImpl().create(entity);
  }

  public void update(DegreeEntity entity) throws SQLException{
    new DegreeDaoImpl().update(entity);
  }

  public void delete(Integer id) throws SQLException{
    new DegreeDaoImpl().delete(id);
  }
}
