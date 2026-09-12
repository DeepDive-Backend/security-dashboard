package org.sangyunpark.securitydashboard.repository;

import org.sangyunpark.securitydashboard.dto.EventTypeCount;
import org.sangyunpark.securitydashboard.dto.HourlyEventCount;

import java.util.List;

public interface AggregationRepository {

    List<EventTypeCount> countByEventType();
    List<HourlyEventCount> countByHour();
}
