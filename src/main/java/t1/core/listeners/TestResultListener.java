package t1.core.listeners;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import t1.core.database.entity.TestResult;
import t1.core.database.service.TestResultService;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TestResultListener implements TestWatcher, BeforeTestExecutionCallback {

    private final TestResultService service = new TestResultService();
    private final Map<String, Long> startTimes = new ConcurrentHashMap<>();

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        startTimes.put(context.getUniqueId(), System.currentTimeMillis());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println(">>> TEST SUCCESS LISTENER CALLED <<<");
        save(context, "PASSED", null);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        save(context, "FAILED", cause.getMessage());
    }

    private void save(ExtensionContext context, String status, String error) {
        long duration =
                System.currentTimeMillis() - startTimes.get(context.getUniqueId());

        TestResult result = TestResult.from(context, status, error, duration);

        UUID dbId = service.save(result);

        Allure.label("dbRecordId", dbId.toString());
        Allure.label("testId", result.getTestId());
    }
}

