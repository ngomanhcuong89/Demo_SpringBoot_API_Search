package com.laptrinhjavaweb.api;

import com.laptrinhjavaweb.converter.BuildingSearchConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.input.BuildingInputDTO;
import com.laptrinhjavaweb.dto.output.BuildingOuputDTO;
import com.laptrinhjavaweb.service.IBuildingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
	
	@Autowired
	private IBuildingService buildingService;
	
	@Autowired
	private BuildingSearchConverter buildingSearchConverter;
	
	@PostMapping
	public BuildingDTO createBuilding(@RequestBody BuildingDTO newBuilding) {
		return buildingService.save(newBuilding);
	}
	
	@GetMapping
	public List<BuildingOuputDTO> findBuilding(	@RequestParam Map<String, Object> params,
												@RequestParam(value="types" , required=false) String[] buildingTypes
												) 
	{
		BuildingInputDTO buildingInputDTO = buildingSearchConverter.paramToDTO(	(String)	params.get("name"), 
																				Integer.valueOf((String)params.get("floorarea")), 
																				(String)	params.get("districtcode"), 
																				(String)	params.get("ward"), 
																				(String)	params.get("street"), 
																				Integer.valueOf((String)params.get("numberofbasement")), 
																				(String)	params.get("direction"),
																				(String)	params.get("level"), 
																				Integer.valueOf((String)params.get("rentareato")), 
																				Integer.valueOf((String)params.get("rentareafrom")), 
																				Integer.valueOf((String)params.get("rentpriceto")), 
																				Integer.valueOf((String)params.get("rentpricefrom")), 
																				(String)	params.get("managername"), 
																				(String)	params.get("managerphone"),
																				Long.valueOf((String)params.get("staffid")),
																				buildingTypes
																				);
		
		List<BuildingOuputDTO> buildingOuputDTO = buildingService.findAll(buildingInputDTO);
	
		return buildingOuputDTO;
	}

/*
	@GetMapping
	public List<BuildingOuputDTO> findBuilding(
												@RequestParam(value="name", required = false) String name,
												@RequestParam(value="floorarea", required = false) Integer floorArea,				//sua
												@RequestParam(value="districtcode", required = false) String districtCode,
												@RequestParam(value="ward", required = false) String ward,
												@RequestParam(value="street", required = false) String street,
												@RequestParam(value="numberofbasement", required = false) Integer numberOfBasement,	//sua
												@RequestParam(value="direction", required = false) String direction,
												@RequestParam(value="level", required = false) String level,
												@RequestParam(value="rentareato", required = false) Integer rentAreaTo,				//sua
												@RequestParam(value="rentareafrom", required = false) Integer rentAreaFrom,			//sua
												@RequestParam(value="rentpriceto", required = false) Integer rentPriceTo,			//sua
												@RequestParam(value="rentpricefrom", required = false) Integer rentPriceFrom,		//sua
												@RequestParam(value="managerName", required = false) String managerName,
												@RequestParam(value="managerPhone", required = false) String managerPhone,												
												@RequestParam(value="staffid", required = false) Long staffId,
												@RequestParam(value="types", required = false) String []buildingTypes //moi them
												) 
	{
		BuildingInputDTO buildingInputDTO = buildingSearchConverter.paramToDTO(name, floorArea, districtCode, ward, street, numberOfBasement, direction, level, rentAreaTo, rentAreaFrom, rentPriceTo, rentPriceFrom, managerName, managerPhone, staffId, buildingTypes);
		List<BuildingOuputDTO> buildingOuputDTO = buildingService.findAll(buildingInputDTO);
	
		return buildingOuputDTO;
	}
*/
}
