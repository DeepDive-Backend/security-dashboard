package org.sangyunpark.securitydashboard;

import lombok.RequiredArgsConstructor;
import org.sangyunpark.securitydashboard.entity.EventType;
import org.sangyunpark.securitydashboard.entity.Severity;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SecurityEventSeeder {

    private final DataSource dataSource;
    private final Random random = new Random();

    public void seed(int totalCount) {
        String sql =
                """
                INSERT INTO security_event (
                    event_time,
                    event_type,
                    severity,
                    source_ip,
                    destination_ip,
                    user_name,
                    host_name,
                    description,
                    status,
                    created_at
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        int batchSize = 1000;

        try(
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            connection.setAutoCommit(false);

            for (int i = 0; i < totalCount; i++) {
                LocalDateTime now = LocalDateTime.now();

                ps.setTimestamp(1, Timestamp.valueOf(now.minusSeconds(i)));
                ps.setString(2, randomEventType());
                ps.setString(3, randomSeverity());
                ps.setString(4, randomIp());
                ps.setString(5, randomIp());
                ps.setString(6, "user-" + (i % 1000));
                ps.setString(7, "server-" + (i % 20));
                ps.setString(8, "Security event " + i);
                ps.setString(9, "OPEN");
                ps.setTimestamp(10, Timestamp.valueOf(now));

                ps.addBatch();

                if(i % batchSize == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }

            ps.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException("SecurityEvent 시드 데이터 생성 실패", e);
        }
    }

    private String randomEventType() {

        int value = random.nextInt(100);

        if (value < 35) {
            return EventType.BRUTE_FORCE.name();
        }

        if (value < 60) {
            return EventType.UNAUTHORIZED_ACCESS.name();
        }

        if (value < 75) {
            return EventType.SQL_INJECTION.name();
        }

        if (value < 85) {
            return EventType.XSS.name();
        }

        if (value < 95) {
            return EventType.MALWARE.name();
        }

        return EventType.DDOS.name();
    }

    private String randomSeverity() {
        int value = random.nextInt(100);

        if (value < 50) {
            return Severity.LOW.name();
        }

        if (value < 80) {
            return Severity.MEDIUM.name();
        }

        if (value < 95) {
            return Severity.HIGH.name();
        }

        return Severity.CRITICAL.name();
    }

    private String randomIp() {
        return String.format(
                "%d.%d.%d.%d",
                random.nextInt(1, 256),
                random.nextInt(0, 256),
                random.nextInt(0, 256),
                random.nextInt(1, 255)
        );
    }
}
