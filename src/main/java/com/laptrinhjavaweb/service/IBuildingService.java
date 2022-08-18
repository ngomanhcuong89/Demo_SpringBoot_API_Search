package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.input.BuildingInputDTO;
import com.laptrinhjavaweb.dto.output.BuildingOuputDTO;

public interface IBuildingService {
	Map<String, String> getDistricts();
	Map<String, String> getBuildingTypes();
	BuildingDTO save(BuildingDTO newBuilding);
	List<BuildingOuputDTO> findAll(BuildingInputDTO buildingInputDTO);
}
