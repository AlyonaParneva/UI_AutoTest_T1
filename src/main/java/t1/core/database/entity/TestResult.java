package t1.core.database.entity;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.ExtensionContext;
import t1.core.utils.TestIdResolver;

import java.util.UUID;

public class TestResult {
    private UUID id;
    private String testId;
    private String testName;
    private String status;
    private String errorMessage;
    private long durationMs;
    private String browser;
    private String environment;

    public UUID getId() {
        return id;
    }

    public String getTestId() {
        return testId;
    }

    public String getTestName() {
        return testName;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public String getBrowser() {
        return browser;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public static TestResult from(
            ExtensionContext context,
            String status,
            String error,
            long duration
    ) {
        TestResult r = new TestResult();
        r.setId(UUID.randomUUID());
        r.setTestId(TestIdResolver.resolve(context));
        r.setTestName(context.getDisplayName());
        r.setStatus(status);
        r.setErrorMessage(error);
        r.setDurationMs(duration);
        r.setBrowser(Configuration.browser);
        String environment = System.getProperty(
                "environment",
                System.getenv().getOrDefault("ENVIRONMENT", "local")
        );
        r.setEnvironment(environment);
        return r;
    }

}
