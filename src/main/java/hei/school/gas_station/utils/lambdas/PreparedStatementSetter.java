package hei.school.gas_station.utils.lambdas;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementSetter {
    void setStatement(PreparedStatement ps) throws SQLException;
}
