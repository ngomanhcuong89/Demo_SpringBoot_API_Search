package com.laptrinhjavaweb.repository.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.dto.input.BuildingInputDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

@Repository
public interface BuildingRepositoryJDBC 
{
	List<BuildingEntity> findAll(BuildingInputDTO buildingInputDTO);
}
