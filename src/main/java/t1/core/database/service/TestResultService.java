package t1.core.database.service;

import t1.core.database.entity.ErrorType;
import t1.core.database.entity.TestResult;
import t1.core.database.repository.TestResultRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TestResultService {

    private final TestResultRepository repository = new TestResultRepository();

    public UUID save(TestResult result) {
        System.out.println(">>> SAVING RESULT TO DB: " + result);
        repository.save(result);
        return result.getId();
    }

    public Map<String, Integer> getStatusStats(
            LocalDateTime from,
            LocalDateTime to
    ) {
        return repository.countByStatus(from, to);
    }

    public List<TestResultRepository.TagStatusStat> getTagStats(
            LocalDateTime from,
            LocalDateTime to
    ) {
        return repository.countByTagAndStatus(from, to);
    }

    public List<TestResultRepository.TagDurationStat> getDurationStats(
            LocalDateTime from,
            LocalDateTime to
    ) {
        return repository.durationStats(from, to);
    }

    public Map<ErrorType, Long> getErrorStats(
            LocalDateTime from,
            LocalDateTime to
    ) {
        return repository.getAllErrorMessages(from, to)
                .stream()
                .map(ErrorClassifier::resolve)
                .collect(Collectors.groupingBy(
                        e -> e,
                        Collectors.counting()
                ));
    }

}

