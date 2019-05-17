package kolisnyk.task.dao.implementation;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import kolisnyk.task.dao.BaseDAO;
import java.lang.reflect.ParameterizedType;
import kolisnyk.task.persistant.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class BaseDaoImp<T, ID extends Serializable> implements BaseDAO<T, ID> {

  protected Class<T> entityClass;

  public Class<T> getEntityClass() {
    if (entityClass == null) {
      //only works if one extends BaseDao, we will take care of it with CDI
      entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
          .getActualTypeArguments()[0];
    }
    return entityClass;
  }

  @Override
  public List<T> findAll() throws SQLException {
    List<T> list = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
      criteria.from(getEntityClass());
      list = session.createQuery(criteria).getResultList();
    }
    return list;
  }


  @Override
  public T findById(ID id) throws SQLException {
    T entity = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      entity = session.get(getEntityClass(),id);
    }
    return entity;
  }

  @Override
  public void create(T entity) throws SQLException {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // start a transaction
      transaction = session.beginTransaction();
      // save the student object
      session.save(entity);
      // commit transaction
      transaction.commit();
    }
  }

  @Override
  public void update(T entity) throws SQLException {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // start a transaction
      transaction = session.beginTransaction();
      // save the student object
      session.update(entity);
      // commit transaction
      transaction.commit();
    }
  }

  /**
   *
   * @param id - the identification  of entity
   * @return true if successful, false - otherwise
   * @throws SQLException
   */
  @Override
  public void delete(ID id) throws SQLException {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      T obj= session.get(getEntityClass(), id);
      if (obj!=null){
        transaction = session.beginTransaction();
        // save the student object
        session.delete(obj);
        // commit transaction
        transaction.commit();
      }
      // start a transaction
    }
  }
}
