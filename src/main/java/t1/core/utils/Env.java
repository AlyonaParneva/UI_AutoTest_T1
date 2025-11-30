package t1.core.utils;

import java.util.ResourceBundle;

public class Env {
    private static final ResourceBundle R = ResourceBundle.getBundle("config");

    public static String str(String key, String def) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) return sys;
        try { return R.getString(key); } catch (Exception ignored) { return def; }
    }
}
