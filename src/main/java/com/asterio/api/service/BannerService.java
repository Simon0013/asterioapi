package com.asterio.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import com.asterio.api.model.Banner;
import com.asterio.api.repository.BannerRepository;

@Service
@Transactional
public class BannerService {
	@Autowired
	private BannerRepository bannerRepository;
	
	public List<Banner> listAllBanners() {
		List<Banner> banners = new ArrayList<Banner>();
		for (Banner banner: bannerRepository.findAll()) {
			if (!banner.getDeleted())
				banners.add(banner);
		}
		return banners;
	}
	
	public void saveBanner(Banner banner) {
		bannerRepository.save(banner);
	}
	
	public Banner getBanner(Long id) {
		Banner banner = bannerRepository.findById(id).get();
		if (!banner.getDeleted())
			return banner;
		return null;
	}
}
