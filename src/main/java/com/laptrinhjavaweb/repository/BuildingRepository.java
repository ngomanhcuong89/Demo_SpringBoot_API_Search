package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.jdbc.BuildingRepositoryJDBC;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends BuildingRepositoryJDBC, JpaRepository<BuildingEntity, Long> {
}
