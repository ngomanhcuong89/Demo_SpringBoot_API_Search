package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.DistrictEntity;
import com.laptrinhjavaweb.repository.jdbc.DistrictRepositoryJDBC;

public interface DistrictRepository extends DistrictRepositoryJDBC, JpaRepository<DistrictEntity, Long>
{

}
