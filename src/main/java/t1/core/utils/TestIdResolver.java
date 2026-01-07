package t1.core.utils;

import org.junit.jupiter.api.extension.ExtensionContext;

public class TestIdResolver {

    public static String resolve(ExtensionContext context) {
        return context.getTags().stream()
                .filter(t -> t.startsWith("NAV") || t.startsWith("SMOKE") || t.startsWith("MAIN") || t.startsWith("PRD")
                      ||  t.startsWith("CASE") || t.startsWith("ABOUT") || t.startsWith("CNT") || t.startsWith("MED")
                        || t.startsWith("AR"))
                .findFirst()
                .orElse("UNKNOWN");
    }
}

