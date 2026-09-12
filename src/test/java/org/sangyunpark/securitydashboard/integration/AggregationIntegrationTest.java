package org.sangyunpark.securitydashboard.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql("/sql/aggregation-test-data.sql")
public class AggregationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void JPQL_이벤트_유형별_집계_API를_조회한다() throws Exception {
        mockMvc.perform(
                        get("/aggregation/event-type")
                                .param("type", "JPQL")
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$[?(@.eventType == 'BRUTE_FORCE')].count")
                        .value(hasItem(2)))
                .andExpect(jsonPath("$[?(@.eventType == 'SQL_INJECTION')].count")
                        .value(hasItem(2)))
                .andExpect(jsonPath("$[?(@.eventType == 'DDOS')].count")
                        .value(hasItem(1)));
    }

    @Test

    void Native_이벤트_유형별_집계_API를_조회한다() throws Exception {

        mockMvc.perform(get("/aggregation/event-type").param("type", "NATIVE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.eventType == 'BRUTE_FORCE')].count")
                        .value(hasItem(2)))
                .andExpect(jsonPath("$[?(@.eventType == 'SQL_INJECTION')].count")
                        .value(hasItem(2)))
                .andExpect(jsonPath("$[?(@.eventType == 'DDOS')].count")
                        .value(hasItem(1)));
    }
}
