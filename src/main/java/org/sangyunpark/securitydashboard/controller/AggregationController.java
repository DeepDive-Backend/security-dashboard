package org.sangyunpark.securitydashboard.controller;

import lombok.RequiredArgsConstructor;
import org.sangyunpark.securitydashboard.dto.EventTypeCount;
import org.sangyunpark.securitydashboard.dto.HourlyEventCount;
import org.sangyunpark.securitydashboard.service.AggregationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aggregation")
@RequiredArgsConstructor
public class AggregationController {

    private final AggregationService aggregationService;

    @GetMapping("/event-type")
    public List<EventTypeCount> countByEventType(
            @RequestParam AggregationType type
    ) {
        return aggregationService.countByEventType(type);
    }

    @GetMapping("/hour")
    public List<HourlyEventCount> countByHour(
            @RequestParam AggregationType type
    ) {
        return aggregationService.countByHour(type);
    }
}
