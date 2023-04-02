package com.asterio.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import com.asterio.api.model.LogRecord;
import com.asterio.api.repository.LogRecordRepository;

@Service
@Transactional
public class LogRecordService {
	@Autowired
	private LogRecordRepository logRecordRepository;
	
	public List<LogRecord> listAllRecords() {
		return logRecordRepository.findAll();
	}
	
	public void saveLogRecord(LogRecord record) {
		logRecordRepository.save(record);
	}
	
	public LogRecord getLogRecord(Long id) {
		return logRecordRepository.findById(id).get();
	}
	
	public void deleteLogRecord(Long id) {
		logRecordRepository.deleteById(id);
	}
}
