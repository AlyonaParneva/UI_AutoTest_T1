package t1.core.database.repository;

import t1.core.database.datasource.DataSourceProvider;
import t1.core.database.entity.TestResult;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    private final DataSource dataSource = DataSourceProvider.getDataSource();

    public Map<String, Integer> countByStatus(
            LocalDateTime from,
            LocalDateTime to
    ) {
        String sql = """
        SELECT status, COUNT(*) 
        FROM test_results
        WHERE created_at BETWEEN ? AND ?
        GROUP BY status
    """;

        Map<String, Integer> result = new HashMap<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(from));
            ps.setTimestamp(2, Timestamp.valueOf(to));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getString("status"),
                        rs.getInt("count"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<TagStatusStat> countByTagAndStatus(
            LocalDateTime from,
            LocalDateTime to
    ) {
        String sql = """
        SELECT
            SPLIT_PART(test_id, '_', 1) AS tag,
            status,
            COUNT(*) AS cnt
        FROM test_results
        WHERE created_at BETWEEN ? AND ?
        GROUP BY tag, status
        ORDER BY tag
    """;

        List<TagStatusStat> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(from));
            ps.setTimestamp(2, Timestamp.valueOf(to));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new TagStatusStat(
                        rs.getString("tag"),
                        rs.getString("status"),
                        rs.getInt("cnt")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка countByTagAndStatus", e);
        }

        return result;
    }

    public List<TagDurationStat> durationStats(
            LocalDateTime from,
            LocalDateTime to
    ) {
        String sql = """
        SELECT
            SPLIT_PART(test_id, '_', 1) AS tag,
            AVG(duration_ms) AS avg_time,
            MAX(duration_ms) AS max_time
        FROM test_results
        WHERE created_at BETWEEN ? AND ?
        GROUP BY tag
        ORDER BY avg_time DESC
    """;

        List<TagDurationStat> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(from));
            ps.setTimestamp(2, Timestamp.valueOf(to));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new TagDurationStat(
                        rs.getString("tag"),
                        rs.getLong("avg_time"),
                        rs.getLong("max_time")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка durationStats", e);
        }

        return result;
    }

    public List<String> getAllErrorMessages(
            LocalDateTime from,
            LocalDateTime to
    ) {
        String sql = """
        SELECT error_message
        FROM test_results
        WHERE status = 'FAILED'
          AND error_message IS NOT NULL
          AND created_at BETWEEN ? AND ?
    """;

        List<String> errors = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(from));
            ps.setTimestamp(2, Timestamp.valueOf(to));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                errors.add(rs.getString(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка getAllErrorMessages", e);
        }

        return errors;
    }

    public record TagStatusStat(
            String tag,
            String status,
            int count
    ) {}

    public record TagDurationStat(
            String tag,
            long avgMs,
            long maxMs
    ) {}

}

