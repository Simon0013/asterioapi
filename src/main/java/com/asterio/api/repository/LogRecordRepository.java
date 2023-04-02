package com.asterio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asterio.api.model.LogRecord;

public interface LogRecordRepository extends JpaRepository<LogRecord, Long> {

}
