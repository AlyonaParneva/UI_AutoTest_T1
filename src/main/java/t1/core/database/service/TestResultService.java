package t1.core.database.service;

import t1.core.database.entity.TestResult;
import t1.core.database.repository.TestResultRepository;

import java.util.UUID;

public class TestResultService {

    private final TestResultRepository repository = new TestResultRepository();

    public UUID save(TestResult result) {
        System.out.println(">>> SAVING RESULT TO DB: " + result);
        repository.save(result);
        return result.getId();
    }
}

