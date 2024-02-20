package com.illunex.factsheet.admin.domain.common.repository;

import com.illunex.factsheet.admin.domain.common.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
}
