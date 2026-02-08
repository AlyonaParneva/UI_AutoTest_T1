package t1.core.database.service;

import t1.core.database.entity.ErrorType;
import t1.core.database.repository.TestResultRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyticsReportGenerator {

    private static final TestResultService service =
            new TestResultService();

    public static void generate() {

        LocalDateTime from = LocalDateTime.now().minusDays(7);
        LocalDateTime to = LocalDateTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        String fromFormatted = from.format(formatter);
        String toFormatted = to.format(formatter);
        String nowFormatted = LocalDateTime.now().format(formatter);

        StringBuilder report = new StringBuilder();

        report.append("ОТЧЁТ ПО АНАЛИТИКЕ АВТОМАТИЗИРОВАННЫХ UI-ТЕСТОВ\n");
        report.append("==================================================\n");
        report.append("Период анализа: с ")
                .append(fromFormatted)
                .append(" по ")
                .append(toFormatted)
                .append("\n");
        report.append("Дата формирования отчёта: ")
                .append(nowFormatted)
                .append("\n\n");

        report.append("1. ОБЩАЯ СТАТИСТИКА ВЫПОЛНЕНИЯ ТЕСТОВ\n");
        report.append("--------------------------------------------------\n");

        Map<String, Integer> statusStats = service.getStatusStats(from, to);

        int passed = statusStats.getOrDefault("PASSED", 0);
        int failed = statusStats.getOrDefault("FAILED", 0);
        int total = passed + failed;

        double successRate = total == 0 ? 0 : (passed * 1.0 / total);
        double passedPercent = successRate * 100;
        double failedPercent = 100 - passedPercent;

        report.append("Всего выполнено тестов: ").append(total).append("\n");
        report.append(String.format(
                "PASSED: %d (%.1f%%)\n",
                passed, passedPercent
        ));
        report.append(String.format(
                "FAILED: %d (%.1f%%)\n",
                failed, failedPercent
        ));
        report.append(String.format(
                "Коэффициент успешного выполнения: %.2f\n\n",
                successRate
        ));

        report.append("Интерпретация стабильности:\n");

        if (successRate >= 0.95) {
            report.append("Система демонстрирует высокий уровень стабильности.\n\n");
        } else if (successRate >= 0.90) {
            report.append(
                    "Процент успешных запусков высокий, однако наличие сбоев\n" +
                            "в отдельных функциональных областях требует внимания.\n\n"
            );
        } else {
            report.append(
                    "Обнаружено значительное количество сбоев,\n" +
                            "что указывает на нестабильное состояние системы.\n\n"
            );
        }

        report.append("2. АНАЛИТИКА ПО ФУНКЦИОНАЛЬНЫМ ТЕГАМ\n");
        report.append("--------------------------------------------------\n");

        Map<String, List<TestResultRepository.TagStatusStat>> tagsGrouped =
                service.getTagStats(from, to).stream()
                        .collect(Collectors.groupingBy(
                                stat -> stat.tag().split("-")[0]
                        ));

        boolean smokeFailed = false;
        boolean navCritical = false;

        for (var entry : tagsGrouped.entrySet()) {
            String tag = entry.getKey();
            List<TestResultRepository.TagStatusStat> stats = entry.getValue();

            int tagPassed = stats.stream()
                    .filter(s -> "PASSED".equals(s.status()))
                    .mapToInt(TestResultRepository.TagStatusStat::count)
                    .sum();

            int tagFailed = stats.stream()
                    .filter(s -> "FAILED".equals(s.status()))
                    .mapToInt(TestResultRepository.TagStatusStat::count)
                    .sum();

            int tagTotal = tagPassed + tagFailed;
            double failRate = tagTotal == 0 ? 0 : (tagFailed * 100.0 / tagTotal);

            if ("SMOKE".equals(tag) && tagFailed > 0) smokeFailed = true;
            if ("NAV".equals(tag) && failRate >= 20) navCritical = true;

            report.append("Тег: ").append(tag).append("\n");
            report.append("Всего тестов: ").append(tagTotal).append("\n");
            report.append("PASSED: ").append(tagPassed).append("\n");
            report.append("FAILED: ").append(tagFailed).append("\n");
            report.append(String.format("Процент ошибок: %.1f%%\n", failRate));

            report.append("Оценка состояния: ");
            if (failRate == 0) {
                report.append("СТАБИЛЬНО\n\n");
            } else if (failRate < 10) {
                report.append("НЕЗНАЧИТЕЛЬНЫЕ ОТКЛОНЕНИЯ\n\n");
            } else {
                report.append("НЕСТАБИЛЬНО\n\n");
            }
        }

        report.append("3. АНАЛИТИКА ПРОИЗВОДИТЕЛЬНОСТИ\n");
        report.append("--------------------------------------------------\n");

        List<TestResultRepository.TagDurationStat> durationStats =
                service.getDurationStats(from, to);

        report.append("Наиболее ресурсоёмкие сценарии:\n");

        durationStats.stream()
                .sorted(Comparator.comparingLong(
                        TestResultRepository.TagDurationStat::avgMs
                ).reversed())
                .limit(5)
                .forEach(stat ->
                        report.append(String.format(
                                "• %s — среднее: %.1f сек, максимум: %.1f сек\n",
                                stat.tag(),
                                stat.avgMs() / 1000.0,
                                stat.maxMs() / 1000.0
                        ))
                );

        report.append("\n");


        report.append("4. АНАЛИТИКА ПРИЧИН СБОЕВ\n");
        report.append("--------------------------------------------------\n");

        Map<ErrorType, Long> errorStats =
                service.getErrorStats(from, to);

        errorStats.forEach((type, count) ->
                report.append(type)
                        .append(" — ")
                        .append(count)
                        .append(" случаев\n")
        );

        report.append("\nИТОГОВОЕ ЗАКЛЮЧЕНИЕ\n");
        report.append("==================================================\n");

        if (smokeFailed) {
            report.append("Обнаружены сбои в smoke-тестах, что является критическим фактором.\n");
        }
        if (navCritical) {
            report.append("Навигационный функционал демонстрирует высокий уровень нестабильности.\n");
        }

        report.append(
                "Основная причина сбоев — превышение времени ожидания,\n" +
                        "что может указывать на проблемы производительности UI или окружения.\n"
        );

        save(report.toString());
    }

    private static void save(String text) {
        try {
            Path path = Path.of("build/analytics/report.txt");
            Files.createDirectories(path.getParent());
            Files.write(path, text.getBytes());
            System.out.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
