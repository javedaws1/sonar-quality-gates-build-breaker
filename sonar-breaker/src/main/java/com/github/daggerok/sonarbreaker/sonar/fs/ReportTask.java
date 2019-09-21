package com.github.daggerok.sonarbreaker.sonar.fs;

import com.github.daggerok.sonarbreaker.infrastructure.Config;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportTask {

    private final Map<String, String> dataHolder = new ConcurrentHashMap<>();

    public static ReportTask of(final String filePath) {
        final String path = Optional.ofNullable(filePath)
                                    .orElse(Config.get("sonar.report.task", "target/sonar/report-task.txt"));
        return new ReportTask().withProperties(path);
    }

    public void printBuildInfo() {
        log.info("Start SonarQube build breaker analysis...");
        dataHolder.forEach((key, value) -> log.info("{}={}", key, value));
    }

    public String getServerUrl() {
        return get("serverUrl");
    }

    public String getCeTaskId() {
        return get("ceTaskId");
    }

    private String get(final String key) {
        return dataHolder.getOrDefault(key, "");
    }

    @SneakyThrows
    private ReportTask withProperties(final String filePath) {
        final @Cleanup FileInputStream inStream = new FileInputStream(filePath);
        final Properties properties = new Properties();
        properties.load(inStream);
        dataHolder.putAll(properties.entrySet().stream().collect(
                Collectors.toMap(e -> e.getKey().toString(),
                                 e -> e.getValue().toString())));
        return this;
    }
}
