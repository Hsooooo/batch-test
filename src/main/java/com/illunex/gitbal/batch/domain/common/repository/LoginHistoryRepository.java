package com.illunex.gitbal.batch.domain.common.repository;

import com.illunex.gitbal.batch.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
}
