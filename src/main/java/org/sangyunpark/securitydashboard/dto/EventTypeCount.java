package org.sangyunpark.securitydashboard.dto;

import org.sangyunpark.securitydashboard.entity.EventType;

public record EventTypeCount(
        EventType eventType,
        long count
) {
}
