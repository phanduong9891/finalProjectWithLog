package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.PaymentUponTermination;
import com.axonactive.roomLeaseManagement.service.dto.PaymentUponTerminationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentUponTerminationMapper {
    PaymentUponTerminationMapper INSTANCE = Mappers.getMapper(PaymentUponTerminationMapper.class);

    PaymentUponTerminationDto toDto(PaymentUponTermination paymentUponTermination);
    List<PaymentUponTermination> toDtos(List<PaymentUponTermination> paymentUponTerminationList);
}
