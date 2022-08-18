package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.entity.BuildingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.input.BuildingInputDTO;
import com.laptrinhjavaweb.dto.output.BuildingOuputDTO;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.DistrictRepository;
import com.laptrinhjavaweb.service.IBuildingService;

@Service
public class BuildingService implements IBuildingService {

	@Autowired
	private BuildingConverter buildingConverter;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public Map<String, String> getDistricts() {
		Map<String, String> districts = new HashMap<>();
		for (DistrictsEnum item: DistrictsEnum.values()) {
			districts.put(item.toString(), item.getDistrictValue());
		}
		return districts;
	}

	@Override
	public Map<String, String> getBuildingTypes() {
		Map<String, String> buildingTypes = new HashMap<>();
		for (BuildingTypesEnum item: BuildingTypesEnum.values()) {
			buildingTypes.put(item.toString(), item.getBuildingTypeValue());
		}
		return buildingTypes;
	}

	@Override
	@Transactional
	public BuildingDTO save(BuildingDTO newBuilding) {
		BuildingEntity buildingEntity = buildingConverter.convertDTOToEntity(newBuilding);
		return buildingConverter.convertEntityToDTO(buildingRepository.save(buildingEntity));
	}

	@Override
	public List<BuildingOuputDTO> findAll(BuildingInputDTO buildingInputDTO) 
	{
		List<BuildingEntity> buildingEntity = buildingRepository.findAll(buildingInputDTO);

		List<BuildingOuputDTO> buildingOutputDTO = new ArrayList<BuildingOuputDTO>();
		for(int i=0; i<buildingEntity.size(); i++) 
		{
			String districtName = districtRepository.getNameById(buildingEntity.get(i).getDistrictId());
			BuildingOuputDTO buildingOutDTO = buildingConverter.convertEntityToOutput(buildingEntity.get(i), districtName);
			buildingOutputDTO.add(buildingOutDTO);
		}
		
		return buildingOutputDTO;
	}

}
