package com.asterio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asterio.api.model.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long> {

}
