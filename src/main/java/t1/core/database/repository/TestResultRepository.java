package t1.core.database.repository;

import t1.core.database.datasource.DataSourceProvider;
import t1.core.database.entity.TestResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestResultRepository {

    public void save(TestResult result) {
        String sql = """
        INSERT INTO test_results
        (id, test_id, test_name, status, error_message,
         duration_ms, browser, environment)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, result.getId());
            ps.setString(2, result.getTestId());
            ps.setString(3, result.getTestName());
            ps.setString(4, result.getStatus());
            ps.setString(5, result.getErrorMessage());
            ps.setLong(6, result.getDurationMs());
            ps.setString(7, result.getBrowser());
            ps.setString(8, result.getEnvironment());

            ps.executeUpdate();

            System.out.println(">>> INSERTED INTO DB");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save test result to DB", e);
        }
    }

}

