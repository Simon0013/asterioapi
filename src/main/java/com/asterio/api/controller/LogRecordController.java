package com.asterio.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.asterio.api.model.LogRecord;
import com.asterio.api.service.LogRecordService;

@RestController
@RequestMapping("/log")
public class LogRecordController {
	@Autowired
	LogRecordService service;
	
	@GetMapping
	public List<LogRecord> list() {
		return service.listAllRecords();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LogRecord> getById(@PathVariable Long id) {
		try {
			LogRecord log = service.getLogRecord(id);
			return new ResponseEntity<LogRecord>(log, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<LogRecord>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	public void add(@RequestBody LogRecord log) {
		service.saveLogRecord(log);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody LogRecord log, @PathVariable Long id) {
		try {
			LogRecord existLog = service.getLogRecord(id);
			log.setId(id);
			service.saveLogRecord(log);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteLogRecord(id);
	}
}
