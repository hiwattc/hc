package com.staroot.hc;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthCheckResultRepository extends JpaRepository<HealthCheckResultEntity, Long> {
}
