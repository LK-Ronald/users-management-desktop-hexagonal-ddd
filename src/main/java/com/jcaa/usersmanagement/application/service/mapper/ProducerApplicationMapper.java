package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateProducerCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteProducerCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetProducerByIdQuery;
import com.jcaa.usersmanagement.application.service.dto.query.GetUserByIdQuery;
import com.jcaa.usersmanagement.domain.enums.ProducerEntityType;
import com.jcaa.usersmanagement.domain.enums.ProducerTypeActivity;
import com.jcaa.usersmanagement.domain.model.Producer;
import com.jcaa.usersmanagement.domain.valueobject.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProducerApplicationMapper {

    public Producer fromCreateCommandToModel(final CreateProducerCommand command) {
        return Producer.create(
                new ProducerId(command.id()),
                new ProducerName(command.name()),
                ProducerEntityType.fromString(command.entityType()),
                ProducerTypeActivity.fromString(command.typeActivity()),
                new ProducerCountryName(command.country()),
                new ProducerCityName(command.city()),
                new ProducerStreetNumber(command.street()),
                new ProducerPostalNumber(command.postal())
        );
    }

    public ProducerId fromGetUserByIdQueryToProducerId(final GetProducerByIdQuery query) {
        return new ProducerId(query.id());
    }

    public ProducerId fromDeleteCommandToProducerId(final DeleteProducerCommand command) {
        return new ProducerId(command.id());
    }
}
