package org.sangyunpark.securitydashboard.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.sangyunpark.securitydashboard.entity.EventType;
import org.sangyunpark.securitydashboard.dto.EventTypeCount;
import org.sangyunpark.securitydashboard.dto.HourlyEventCount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NativeAggregationRepository implements AggregationRepository {

    private final EntityManager em;

    @Override
    public List<EventTypeCount> countByEventType() {
        List<Object[]> results = em.createNativeQuery(
                """
                    SELECT event_type, COUNT(*)
                    FROM security_event
                    GROUP BY event_type
                """)
                .getResultList();

        return results.stream()
                .map(row -> new EventTypeCount(
                        EventType.valueOf((String) row[0]),
                        ((Number) row[1]).longValue()

                ))

                .toList();
    }

    @Override
    public List<HourlyEventCount> countByHour() {
        List<Object[]> results = em.createNativeQuery(
                """
                    SELECT HOUR(event_time), COUNT(*)
                    FROM security_event
                    GROUP BY HOUR(event_time)
                    ORDER BY HOUR(event_time)
                """)
                .getResultList();

        return results.stream()
                .map(row -> new HourlyEventCount(
                        ((Number) row[0]).intValue(),
                        ((Number) row[1]).longValue()
                ))
                .toList();
    }
}
