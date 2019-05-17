package kolisnyk.task.dao;

import java.sql.SQLException;
import java.util.List;
import kolisnyk.task.entity.LectorEntity;

public interface LectorDAO  {

  List<LectorEntity> findByTemplate(String template)  throws SQLException;
}
