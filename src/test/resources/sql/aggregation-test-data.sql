DELETE FROM security_event;

INSERT INTO security_event (
    event_time,
    event_type,
    severity,
    source_ip,
    destination_ip,
    user_name,
    host_name,
    description,
    status,
    created_at
)
VALUES
    ('2026-09-12 10:00:00', 'BRUTE_FORCE', 'HIGH', '192.168.0.10', '10.0.0.10', 'test-user', 'test-server', 'test', 'OPEN', '2026-09-12 10:00:00'),
    ('2026-09-12 10:00:00', 'BRUTE_FORCE', 'HIGH', '192.168.0.11', '10.0.0.10', 'test-user', 'test-server', 'test', 'OPEN', '2026-09-12 10:00:00'),
    ('2026-09-12 11:00:00', 'SQL_INJECTION', 'HIGH', '192.168.0.12', '10.0.0.10', 'test-user', 'test-server', 'test', 'OPEN', '2026-09-12 11:00:00'),
    ('2026-09-12 12:00:00', 'SQL_INJECTION', 'HIGH', '192.168.0.13', '10.0.0.10', 'test-user', 'test-server', 'test', 'OPEN', '2026-09-12 12:00:00'),
    ('2026-09-12 12:00:00', 'DDOS', 'HIGH', '192.168.0.14', '10.0.0.10', 'test-user', 'test-server', 'test', 'OPEN', '2026-09-12 12:00:00');