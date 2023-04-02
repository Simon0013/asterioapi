package com.asterio.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.asterio.api.model.*;
import com.asterio.api.service.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/bid")
public class BannerController {
	@Autowired
	BannerService service;
	
	@Autowired
	BannerInCategoryService bicService;
	
	@Autowired
	LogRecordService logService;
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping
	public List<Banner> list() {
		return service.listAllBanners();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Banner> getById(@PathVariable Long id) {
		try {
            Banner banner = service.getBanner(id);
            return new ResponseEntity<Banner>(banner, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Banner>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping
	public ResponseEntity<List<Banner>> getByCategories(@RequestParam List<Long> cat) {
		List<Banner> banners = new ArrayList<Banner>();
		List<Long> bannerIds = new ArrayList<Long>();
		try {
			for (long category: cat) {
				List<BannerInCategory> bics = bicService.listAllByCategory(category);
				for (BannerInCategory bic: bics) {
					if (!bannerIds.contains(bic.getBanner())) {
						banners.add(service.getBanner(bic.getBanner()));
						bannerIds.add(bic.getBanner());
					}
				}
			}
			return new ResponseEntity<List<Banner>>(banners, HttpStatus.OK);
		} catch (NoSuchElementException e) {
            return new ResponseEntity<List<Banner>>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping("/")
	public void add(@RequestBody Banner banner) {
		Date requestTime = new Date();
		service.saveBanner(banner);
		logService.saveLogRecord(GenerateNewLogRecord(requestTime, null));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Banner banner, @PathVariable Long id) {
		try {
			Date requestTime = new Date();
			Banner existBanner = service.getBanner(id);
			banner.setId(id);
			service.saveBanner(banner);
			logService.saveLogRecord(GenerateNewLogRecord(requestTime, null));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			Date requestTime = new Date();
			Banner existBanner = service.getBanner(id);
			existBanner.setDeleted(true);
			service.saveBanner(existBanner);
			logService.saveLogRecord(GenerateNewLogRecord(requestTime, null));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	private LogRecord GenerateNewLogRecord(Date requestTime, String noContentReason) {
		LogRecord newLog = new LogRecord();
		newLog.setIp(request.getRemoteAddr());
		newLog.setUserAgent(request.getHeader("User-Agent"));
		newLog.setRequestTime(requestTime);
		long id_banner = 0;
		for (Banner bann: service.listAllBanners()) {
			if (bann.getId() > id_banner)
				id_banner = bann.getId();
		}
		newLog.setIdBanner(id_banner);
		newLog.setNoContentReason(noContentReason);
		return newLog;
	}
}
