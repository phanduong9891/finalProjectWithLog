package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.Payable;
import com.axonactive.roomLeaseManagement.service.dto.PayableDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PayableMapper {
    PayableMapper INSTANCE = Mappers.getMapper(PayableMapper.class);

    @Mapping(source = "monthlyPayment.month",target = "month")

    PayableDto toDto(Payable payable);
    List<PayableDto> toDtos(List<Payable> payableList);
}
