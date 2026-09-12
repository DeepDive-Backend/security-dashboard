package org.sangyunpark.securitydashboard.unit;

import org.junit.jupiter.api.Test;
import org.sangyunpark.securitydashboard.entity.EventType;
import org.sangyunpark.securitydashboard.repository.JpqlAggregationRepository;
import org.sangyunpark.securitydashboard.repository.NativeAggregationRepository;
import org.sangyunpark.securitydashboard.dto.EventTypeCount;
import org.sangyunpark.securitydashboard.dto.HourlyEventCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Sql("/sql/aggregation-test-data.sql")
class AggregationRepositoryTest {

    @Autowired
    private JpqlAggregationRepository jpqlRepository;

    @Autowired
    private NativeAggregationRepository nativeRepository;

    @Test
    void JPQL_이벤트_유형별_집계가_정상적으로_동작한다() {

        List<EventTypeCount> result =
                jpqlRepository.countByEventType();

        assertThat(result)
                .containsExactlyInAnyOrder(
                        new EventTypeCount(EventType.BRUTE_FORCE, 2L),
                        new EventTypeCount(EventType.SQL_INJECTION, 2L),
                        new EventTypeCount(EventType.DDOS, 1L)
                );
    }

    @Test
    void Native_이벤트_유형별_집계가_정상적으로_동작한다() {

        List<EventTypeCount> result =
                nativeRepository.countByEventType();

        assertThat(result)
                .containsExactlyInAnyOrder(
                        new EventTypeCount(EventType.BRUTE_FORCE, 2L),
                        new EventTypeCount(EventType.SQL_INJECTION, 2L),
                        new EventTypeCount(EventType.DDOS, 1L)
                );
    }

    @Test
    void JPQL_시간대별_집계가_정상적으로_동작한다() {

        List<HourlyEventCount> result =
                jpqlRepository.countByHour();

        assertThat(result)
                .containsExactly(
                        new HourlyEventCount(10, 2L),
                        new HourlyEventCount(11, 1L),
                        new HourlyEventCount(12, 2L)
                );
    }

    @Test
    void Native_시간대별_집계가_정상적으로_동작한다() {

        List<HourlyEventCount> result =
                nativeRepository.countByHour();

        assertThat(result)
                .containsExactly(
                        new HourlyEventCount(10, 2L),
                        new HourlyEventCount(11, 1L),
                        new HourlyEventCount(12, 2L)
                );
    }
}