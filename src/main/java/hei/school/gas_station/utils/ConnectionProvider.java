package hei.school.gas_station.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Configuration
public class ConnectionProvider {
    

    @Bean
    public Connection getConnection() throws SQLException {
        String username = System.getenv("PG_USERNAME");
        String password = System.getenv("PG_PASSWORD");
        String databaseUrl = System.getenv("PG_DATABASE_URL");
        return DriverManager.getConnection(
            String.format("jdbc:postgresql://%s", databaseUrl),
            username,
            password
        );
    }
}