package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.MonthlyServiceUsing;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyServiceUsingDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MonthlyServiceUsingMapper {
    MonthlyServiceUsingMapper INSTANCE = Mappers.getMapper(MonthlyServiceUsingMapper.class);

    MonthlyServiceUsingDto toDto(MonthlyServiceUsing monthlyServiceUsing);
    List<MonthlyServiceUsingDto> toDtos(List<MonthlyServiceUsing> monthlyServiceUsingList);
}
