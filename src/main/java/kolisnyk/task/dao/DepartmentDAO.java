package kolisnyk.task.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import kolisnyk.task.entity.DegreeEntity;
import kolisnyk.task.entity.LectorEntity;

public interface DepartmentDAO {

  /**
   * Find head of Department.
   * Can be situation when there are many same department's name.
   *
   * @param departmentName name of Department
   * @return
   */
  List<LectorEntity> findHeadByName(String departmentName)  throws SQLException;


  int countLectoresDegee(String departmentName, DegreeEntity degree)  throws SQLException;

  /**
   *
   * @param departmentName name of Department
   * @return object if average salary, null if departmentName not correct or no data
   */
  BigDecimal averageSalary(String departmentName)  throws SQLException;


  int countOfEmployee(String departmentName)  throws SQLException;

}
