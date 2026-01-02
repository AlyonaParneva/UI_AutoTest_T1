package t1.core.utils;

import org.junit.jupiter.api.extension.ExtensionContext;

public class TestIdResolver {

    public static String resolve(ExtensionContext context) {
        return context.getTags().stream()
                .filter(t -> t.startsWith("NAV") || t.startsWith("HDR"))
                .findFirst()
                .orElse("UNKNOWN");
    }
}

