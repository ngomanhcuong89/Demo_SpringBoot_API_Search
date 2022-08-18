package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.input.BuildingInputDTO;

@Component
public class BuildingSearchConverter 
{
	public BuildingInputDTO paramToDTO(String name, Integer floorArea, String districtCode, String ward , String street, Integer numberOfBasement, String direction, String level, Integer rentAreaTo, Integer rentAreaFrom, Integer rentPriceTo, Integer rentPriceFrom, String managerName, String managerPhone, Long staffId, String[] buildingTypes) 
	{
		BuildingInputDTO buildingInputDTO = new BuildingInputDTO();
		buildingInputDTO.setName(name);
		buildingInputDTO.setFloorArea(floorArea);
		buildingInputDTO.setDistrictCode(districtCode);
		buildingInputDTO.setWard(ward);		
		buildingInputDTO.setStreet(street);
		buildingInputDTO.setNumberOfBasement(numberOfBasement);
		buildingInputDTO.setDirection(direction);
		buildingInputDTO.setLevel(level);
		buildingInputDTO.setRentAreaTo(rentAreaTo);
		buildingInputDTO.setRentAreaFrom(rentAreaFrom);
		buildingInputDTO.setRentPriceTo(rentPriceTo);
		buildingInputDTO.setRentPriceFrom(rentPriceFrom);
		buildingInputDTO.setManagerName(managerName);
		buildingInputDTO.setManagerPhone(managerPhone);
		buildingInputDTO.setStaffId(staffId);
		buildingInputDTO.setBuildingTypes(buildingTypes);
		
		return buildingInputDTO;
	}
}