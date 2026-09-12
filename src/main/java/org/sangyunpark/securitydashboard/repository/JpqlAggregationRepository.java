package org.sangyunpark.securitydashboard.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.sangyunpark.securitydashboard.dto.EventTypeCount;
import org.sangyunpark.securitydashboard.dto.HourlyEventCount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpqlAggregationRepository implements AggregationRepository {

    private final EntityManager em;

    @Override
    public List<EventTypeCount> countByEventType() {
        return em.createQuery("""
                    select new org.sangyunpark.securitydashboard.repository.dto.EventTypeCount(
                        e.eventType,
                        count(e)
                    )
                    from SecurityEvent e
                    group by e.eventType
                """, EventTypeCount.class)
                .getResultList();
    }

    @Override
    public List<HourlyEventCount> countByHour() {
        return em.createQuery("""
                    select new org.sangyunpark.securitydashboard.repository.dto.HourlyEventCount(
                        hour(e.eventTime),
                        count(e)
                    )
                    from SecurityEvent e
                    group by hour (e.eventTime)
                    order by hour (e.eventTime)
                """, HourlyEventCount.class)
                .getResultList();
    }
}
