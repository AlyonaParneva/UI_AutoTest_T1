package t1.core.database.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import t1.core.config.DbConfig;
import t1.core.utils.PropertyReader;

import javax.sql.DataSource;

public class DataSourceProvider {

    private static HikariDataSource dataSource;

    public static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(PropertyReader.get("db.url"));
            config.setUsername(PropertyReader.get("db.user"));
            config.setPassword(PropertyReader.get("db.password"));

            config.setMaximumPoolSize(2);
            config.setConnectionTimeout(10000);

            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }
}


