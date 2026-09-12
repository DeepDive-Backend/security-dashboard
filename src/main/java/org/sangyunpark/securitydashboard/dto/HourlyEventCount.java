package org.sangyunpark.securitydashboard.dto;

public record HourlyEventCount(
        int hour,
        long count
) {
}
