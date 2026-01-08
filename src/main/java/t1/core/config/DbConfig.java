package t1.core.config;

import t1.core.utils.PropertyReader;


public class DbConfig {

    public static final String URL =
            PropertyReader.get("db.url");
    public static final String USER =
            PropertyReader.get("db.user");
    public static final String PASSWORD =
            PropertyReader.get("db.password");
}

