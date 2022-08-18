package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.laptrinhjavaweb.dto.input.BuildingInputDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.jdbc.BuildingRepositoryJDBC;

public class BuildingRepositoryJDBCImpl implements BuildingRepositoryJDBC 
{
	@Override
	public List<BuildingEntity> findAll(BuildingInputDTO buildingInputDTO) 
	{
		List<BuildingEntity> buildingEntity = new ArrayList<>();
		try 
		{
			Connection connection = HelperRepositoryJDBC.getConnection();

			StringBuilder queryBuilder 	= new StringBuilder("select * from building b inner join district d on b.districtid = d.id ");
			queryBuilder = createJoin(buildingInputDTO, queryBuilder);
			queryBuilder = createWhere(buildingInputDTO, queryBuilder);

			String query = queryBuilder.toString().trim();
			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(query);
			while(rs.next()==true) 
			{
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("b.name"));
				building.setStreet(rs.getString("b.street"));
				building.setWard(rs.getString("b.ward"));
				building.setDistrictId(rs.getLong("b.districtid"));
				building.setManagerName(rs.getString("b.managername"));
				building.setManagerPhone(rs.getString("b.managerphone"));
				building.setFloorArea(rs.getInt("b.floorarea"));
				building.setRentPrice(rs.getInt("b.rentprice"));
				building.setServiceFee(rs.getString("b.servicefee"));
				building.setBrokerageFee(rs.getString("b.brokeragefee"));
				
				buildingEntity.add(building);
			}			
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return buildingEntity;
	}
	
	public StringBuilder createJoin(BuildingInputDTO buildingInputDTO, StringBuilder queryBuilder) 
	{
		StringBuilder join 	= new StringBuilder();
		StringBuilder where = new StringBuilder();
		
		if(buildingInputDTO.getRentAreaTo() != null || buildingInputDTO.getRentAreaFrom() != null) 
		{	
			join.append("inner join rentarea r on b.id = r.buildingid ");
			
			if(buildingInputDTO.getRentAreaTo() != null) 
			{
				where.append(" and "+buildingInputDTO.getRentAreaTo()+" <= r.value");
			}
			if(buildingInputDTO.getRentAreaFrom() != null) 
			{
				where.append(" and r.value <= "+buildingInputDTO.getRentAreaFrom());
			}			
		}	
	
		if(buildingInputDTO.getStaffId() != null) 
		{
			join.append("inner join assignmentbuilding ab on b.id = ab.buildingid inner join user on ab.staffid = user.id inner join user_role ur on ur.userid = user.id inner join role on ur.roleid = role.id ");	
			where.append(" and user.id = "+buildingInputDTO.getStaffId()+" and user.id in (select user.id from user_role where user_role.roleid=2)");
		}
		
		if(buildingInputDTO.getBuildingTypes() != null && buildingInputDTO.getBuildingTypes().length > 0) 
		{
			join.append("inner join buildingrenttype brt on b.id = brt.buildingid inner join renttype rt on brt.renttypeid = rt.id ");
			
			String buildingTypes[] = buildingInputDTO.getBuildingTypes();
			int i = 0;
			for(String item: buildingTypes) 
			{
				buildingTypes[i] = "rt.code like '%"+ item +"%'";
				i++;
			}
			where.append(" and (").append(String.join(" or ", buildingTypes)).append(")");				
		}		
		
		return queryBuilder.append(join).append("where 1=1 ").append(where);
	}
	
	public StringBuilder createWhere(BuildingInputDTO buildingInputDTO, StringBuilder queryBuilder) 
	{	
		if(buildingInputDTO.getName() != null && buildingInputDTO.getName() != "") 
		{
			queryBuilder.append(" and b.name like '%"+buildingInputDTO.getName()+"%'");
		}
		if(buildingInputDTO.getFloorArea() != null)
		{
			queryBuilder.append(" and b.floorarea = "+buildingInputDTO.getFloorArea());
		}
		if(buildingInputDTO.getDistrictCode() != null && buildingInputDTO.getDistrictCode() != "")
		{
			queryBuilder.append(" and d.code like '%"+buildingInputDTO.getDistrictCode()+"%'");
		}
		if(buildingInputDTO.getWard() != null && buildingInputDTO.getWard() != "")
		{
			queryBuilder.append(" and b.ward like '%"+buildingInputDTO.getWard()+"%'");
		}
		if(buildingInputDTO.getStreet() != null && buildingInputDTO.getStreet() != "")
		{
			queryBuilder.append(" and b.street like '%"+buildingInputDTO.getStreet()+"%'");
		}
		if(buildingInputDTO.getNumberOfBasement() != null)
		{
			queryBuilder.append(" and b.numberofbasement = "+buildingInputDTO.getNumberOfBasement());
		}
		if(buildingInputDTO.getDirection() != null && buildingInputDTO.getDirection() != "")
		{
			queryBuilder.append(" and b.direction like '%"+buildingInputDTO.getDirection()+"%'");
		}
		if(buildingInputDTO.getLevel() != null && buildingInputDTO.getLevel() != null)
		{
			queryBuilder.append(" and b.level like '%"+buildingInputDTO.getLevel()+"%'");
		}

		if(buildingInputDTO.getRentPriceTo() != null) 
		{
			queryBuilder.append(" and "+buildingInputDTO.getRentPriceTo()+" <= b.rentprice");
		}
		if(buildingInputDTO.getRentPriceFrom() != null) 
		{
			queryBuilder.append(" and b.rentprice <= "+buildingInputDTO.getRentPriceFrom());
		}
		if(buildingInputDTO.getManagerName() != null && buildingInputDTO.getManagerName() != "") 
		{
			queryBuilder.append(" and b.managername like '%"+buildingInputDTO.getManagerName()+"%'");
		}
		if(buildingInputDTO.getManagerPhone() != null && buildingInputDTO.getManagerPhone() != "") 
		{
			queryBuilder.append(" and b.managerphone like '%"+buildingInputDTO.getManagerPhone()+"%'");
		}

		return queryBuilder.append(" group by b.id");
	}
}
