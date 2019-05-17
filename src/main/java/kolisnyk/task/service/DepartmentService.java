package kolisnyk.task.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import kolisnyk.task.dao.implementation.DepartmentDaoImpl;
import kolisnyk.task.entity.DegreeEntity;
import kolisnyk.task.entity.DepartmentEntity;
import kolisnyk.task.entity.LectorEntity;

public class DepartmentService {
  public  List<DepartmentEntity> findAll() throws SQLException {
    return new DepartmentDaoImpl().findAll();
  }

  public DepartmentEntity findById(Integer id) throws SQLException {
    return new DepartmentDaoImpl().findById(id);
  }

  public void create(DepartmentEntity entity) throws SQLException {
    new DepartmentDaoImpl().create(entity);
  }

  public void update(DepartmentEntity entity) throws SQLException{
    new DepartmentDaoImpl().update(entity);
  }

  public void delete(Integer id) throws SQLException{
    new DepartmentDaoImpl().delete(id);
  }
  public List<LectorEntity> findHeadByName(String departmentName) throws SQLException{
    return new DepartmentDaoImpl().findHeadByName(departmentName) ;
  }

  public BigDecimal averageSalary(String departmentName) throws SQLException{
    return new DepartmentDaoImpl().averageSalary(departmentName);
  }
  public int countOfEmployee(String departmentName)  throws SQLException {
    return new DepartmentDaoImpl().countOfEmployee(departmentName);
  }
  public int countLectoresDegee(String departmentName, DegreeEntity degree) throws SQLException {
    return new DepartmentDaoImpl().countLectoresDegee(departmentName,degree);
  }
}
