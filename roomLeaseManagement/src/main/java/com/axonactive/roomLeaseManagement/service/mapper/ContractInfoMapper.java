package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.ContractInfo;
import com.axonactive.roomLeaseManagement.service.dto.ContractInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContractInfoMapper {
    ContractInfoMapper INSTANCE = Mappers.getMapper(ContractInfoMapper.class);

    @Mapping(source = "room.roomNumber",target = "roomNumber")
    @Mapping(source = "room.roomType",target = "roomType")
    ContractInfoDto toDto(ContractInfo contractInfo);
    List<ContractInfoDto> toDtos(List<ContractInfo> ContractInfo);
}
