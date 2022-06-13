package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.MonthlyPayment;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyPaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MonthlyPaymentMapper {

    MonthlyPaymentMapper INSTANCE = Mappers.getMapper(MonthlyPaymentMapper.class);

    @Mapping(source = "contract.tenant.firstName", target = "tenantFirstName")
    @Mapping(source = "contract.tenant.lastName", target = "tenantLastName")
    MonthlyPaymentDto toDto(MonthlyPayment monthlyPayment);

    List<MonthlyPaymentDto> toDtos(List<MonthlyPayment> monthlyPaymentList);
}
