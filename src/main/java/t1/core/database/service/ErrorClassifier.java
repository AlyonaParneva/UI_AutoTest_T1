package t1.core.database.service;

import t1.core.database.entity.ErrorType;

public class ErrorClassifier {

    public static ErrorType resolve(String errorMessage) {
        if (errorMessage == null) {
            return ErrorType.OTHER;
        }

        String msg = errorMessage.toLowerCase();

        if (msg.contains("timeout")) {
            return ErrorType.TIMEOUT;
        }
        if (msg.contains("xpath")) {
            return ErrorType.XPATH;
        }
        if (msg.contains("element")) {
            return ErrorType.ELEMENT;
        }
        if (msg.contains("connection")
                || msg.contains("environment")
                || msg.contains("refused")) {
            return ErrorType.ENVIRONMENT;
        }
        if (msg.contains("expected")
                || msg.contains("assert")) {
            return ErrorType.ASSERTION;
        }

        return ErrorType.OTHER;
    }

    private ErrorClassifier() {
    }
}
