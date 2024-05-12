package hei.school.gas_station.utils;
import hei.school.gas_station.utils.lambdas.PreparedStatementSetter;
import hei.school.gas_station.utils.lambdas.RowMapper;
import lombok.AllArgsConstructor;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class QueryTemplate {
    private final Connection connection;
    private static final Logger LOGGER = Logger.getLogger(QueryTemplate.class.getName());


    public <T> List<T> executeQuery(String query, PreparedStatementSetter pss, RowMapper<T> rowMapper) {
        ArrayList<T> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            pss.setStatement(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }

        return result;
    }

    public <T> List<T> executeQuery(String query, RowMapper<T> rowMapper) {
        ArrayList<T> result = new ArrayList<>();
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            return Collections.emptyList();
        }

        return result;
    }

    public <T> T executeSingleQuery(String query, RowMapper<T> rowMapper) throws SQLException {
        T result = null;
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = rowMapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }finally{
            try{
              statement.close();
   
            }catch (SQLException e) {
                LOGGER.warning(e.getMessage());
            }
        }
        return result;
    }

    public <T> T executeSingleQuery(String query, PreparedStatementSetter pss, RowMapper<T> rowMapper) {
        T result = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            pss.setStatement(statement);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = rowMapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return result;
    }

    public Integer executeUpdate(String insertStatement, PreparedStatementSetter pss) {
        try {
            PreparedStatement ps = connection.prepareStatement(insertStatement);
            pss.setStatement(ps);
            Integer affectedRows = ps.executeUpdate();
            ps.close();
            return affectedRows;
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }

        return 0;
    }

    public <T> List<T> executeCall(String callStatement, PreparedStatementSetter pss, RowMapper<T> rowMapper) {
        ArrayList<T> result = new ArrayList<>();
        try {
            CallableStatement pc = connection.prepareCall(callStatement);
            pss.setStatement(pc);
            ResultSet resultSet = pc.executeQuery();
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }
            pc.close();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return result;
    }
}
