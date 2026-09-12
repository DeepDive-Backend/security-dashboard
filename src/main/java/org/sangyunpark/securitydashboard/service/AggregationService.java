package org.sangyunpark.securitydashboard.service;

import lombok.RequiredArgsConstructor;
import org.sangyunpark.securitydashboard.controller.AggregationType;
import org.sangyunpark.securitydashboard.repository.AggregationRepository;
import org.sangyunpark.securitydashboard.repository.JpqlAggregationRepository;
import org.sangyunpark.securitydashboard.repository.NativeAggregationRepository;
import org.sangyunpark.securitydashboard.dto.EventTypeCount;
import org.sangyunpark.securitydashboard.dto.HourlyEventCount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AggregationService {

    private final JpqlAggregationRepository jpqlRepository;
    private final NativeAggregationRepository nativeRepository;

    public List<EventTypeCount> countByEventType(AggregationType type) {
        return getRepository(type).countByEventType();
    }

    public List<HourlyEventCount> countByHour(AggregationType type) {
        return getRepository(type).countByHour();
    }

    private AggregationRepository getRepository(AggregationType type) {
        return switch(type) {
            case JPQL -> jpqlRepository;
            case NATIVE ->  nativeRepository;
        };
    }
}