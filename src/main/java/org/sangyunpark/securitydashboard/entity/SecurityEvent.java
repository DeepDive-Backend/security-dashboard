package org.sangyunpark.securitydashboard.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "security_event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SecurityEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime eventTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Severity severity;

    @Column(length = 45)
    private String sourceIp;

    @Column(length = 45)
    private String destinationIp;

    @Column(length = 100)
    private String userName;

    @Column(length = 100)
    private String hostName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EventStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
