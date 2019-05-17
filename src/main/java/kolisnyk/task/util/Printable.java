package kolisnyk.task.util;

import java.sql.SQLException;

@FunctionalInterface
public interface Printable {
    void print() throws SQLException;
}
