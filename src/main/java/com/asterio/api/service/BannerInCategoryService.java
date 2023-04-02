package com.asterio.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import com.asterio.api.model.BannerInCategory;
import com.asterio.api.repository.BannerInCategoryRepository;

@Service
@Transactional
public class BannerInCategoryService {
	@Autowired
	private BannerInCategoryRepository bannerRepository;
	
	public List<BannerInCategory> listAll() {
		return bannerRepository.findAll();
	}
	
	public List<BannerInCategory> listAllByCategory(long id) {
		List<BannerInCategory> bic = listAll();
		List<BannerInCategory> result = new ArrayList<BannerInCategory>();
		for (BannerInCategory rec: bic) {
			if (rec.getCategory() == id)
				result.add(rec);
		}
		return result;
	}
	
	public List<BannerInCategory> listAllByBanner(long id) {
		List<BannerInCategory> bic = listAll();
		List<BannerInCategory> result = new ArrayList<BannerInCategory>();
		for (BannerInCategory rec: bic) {
			if (rec.getBanner() == id)
				result.add(rec);
		}
		return result;
	}
	
	public void saveBannerInCategory(BannerInCategory bic) {
		bannerRepository.save(bic);
	}
	
	public void deleteBannerInCategory(BannerInCategory bic) {
		bannerRepository.delete(bic);
	}
}
