package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.entity.BuildingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.output.BuildingOuputDTO;

@Component
public class BuildingConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO convertEntityToDTO(BuildingEntity entity) {
		BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);
		return dto;
	}
	
	public BuildingEntity convertDTOToEntity(BuildingDTO dto) {
		BuildingEntity entity = modelMapper.map(dto, BuildingEntity.class);
		return entity;
	}
	
	public BuildingOuputDTO convertEntityToOutput(BuildingEntity entity, String nameDistrict) 
	{
		BuildingOuputDTO dto = modelMapper.map(entity, BuildingOuputDTO.class);
		dto.setAddress(entity.getStreet()+", "+entity.getWard()+" "+nameDistrict);
		return dto;
	}

}
