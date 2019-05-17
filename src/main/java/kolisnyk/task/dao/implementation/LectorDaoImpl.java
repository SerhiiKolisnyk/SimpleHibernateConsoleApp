package kolisnyk.task.dao.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kolisnyk.task.dao.LectorDAO;
import kolisnyk.task.entity.LectorEntity;
import kolisnyk.task.persistant.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class LectorDaoImpl extends BaseDaoImp<LectorEntity,Integer>implements LectorDAO {

  @Override
  public List<LectorEntity> findByTemplate(String template)  throws SQLException {
    Transaction transaction = null;
    List<LectorEntity> list=new ArrayList<>();
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // start a transaction
      transaction = session.beginTransaction();
      String hql = "select lc.id, lc.firstName, lc.lastName from LectorEntity lc where lower(lc.firstName) LIKE :searchKeyword OR lower(lc.lastName) LIKE :searchKeyword";
      Query query = session.createQuery(hql).setParameter("searchKeyword", "%"+template.toLowerCase()+"%");
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

}
