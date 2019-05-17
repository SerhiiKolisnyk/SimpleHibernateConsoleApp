package kolisnyk.task.dao.implementation;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kolisnyk.task.dao.DepartmentDAO;
import kolisnyk.task.entity.DegreeEntity;
import kolisnyk.task.entity.DepartmentEntity;
import kolisnyk.task.entity.LectorEntity;
import kolisnyk.task.persistant.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DepartmentDaoImpl extends BaseDaoImp<DepartmentEntity, Integer> implements
    DepartmentDAO {


  @Override
  public List<LectorEntity> findHeadByName(String departmentName) throws SQLException {
    Transaction transaction = null;
    List<LectorEntity> list = new ArrayList<>();
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // start a transaction
      transaction = session.beginTransaction();
      String hql = "select e.head.id,e.head.firstName,e.head.lastName from DepartmentEntity e where e.departmentName=:departmentName";
      Query query = session.createQuery(hql).setParameter("departmentName", departmentName);
      for (Iterator it = query.iterate(); it.hasNext(); ) {
        Object[] row = (Object[]) it.next();
        LectorEntity lectorEntity = new LectorEntity();
        lectorEntity.setId((int) row[0]);
        lectorEntity.setFirstName((String) row[1]);
        lectorEntity.setLastName((String) row[2]);
        list.add(lectorEntity);
      }
      transaction.commit();
    }
    return list;
  }

  @Override
  public int countLectoresDegee(String departmentName, DegreeEntity degree) throws SQLException {
    Transaction transaction = null;
    List<LectorEntity> list = new ArrayList<>();
    int count = 0;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // start a transaction
      transaction = session.beginTransaction();
      String hql = "select count(*) from LectorEntity e inner join e.currentDepartment inner join e.degree as a where e.currentDepartment.departmentName=:departmentName and e.degree.id=:degree";
      count = ((Long) session.createQuery(hql).setParameter("departmentName", departmentName)
          .setParameter("degree", degree.getId()).uniqueResult()).intValue();

      System.out.println(count);
      transaction.commit();
    }
    return 0;
  }

  @Override
  public BigDecimal averageSalary(String departmentName) throws SQLException{
    Transaction transaction = null;
    BigDecimal avg = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // start a transaction
      transaction = session.beginTransaction();
      String hql = "select avg (e.salary) from LectorEntity e inner join e.currentDepartment inner join e.degree as a where e.currentDepartment.departmentName=:departmentName";
      Double avgDouble = (Double) session.createQuery(hql)
          .setParameter("departmentName", departmentName)
          .uniqueResult();
      if (avgDouble != null) {
        avg = new BigDecimal(avgDouble);
      }
      transaction.commit();
    }
    return avg;
  }

  @Override
  public int countOfEmployee(String departmentName)  throws SQLException {
    Transaction transaction = null;
    int count = 0;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // start a transaction
      transaction = session.beginTransaction();
      String hql = "select e.lectores.size from DepartmentEntity e where e.departmentName=:departmentName";
      Object object = session.createQuery(hql).setParameter("departmentName", departmentName)
          .uniqueResult();
      if (object != null) {
        count = (Integer) object;
      }
      transaction.commit();
    }
    return count;
  }

}
