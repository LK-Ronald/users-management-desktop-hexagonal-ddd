package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateProducerCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteProducerCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetProducerByIdQuery;
import com.jcaa.usersmanagement.domain.model.Producer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateProducerRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProducerResponse;

import java.util.List;

public final class ProducerDesktopMapper {
    private ProducerDesktopMapper() {
    }

    public static CreateProducerCommand toCreateCommand(final CreateProducerRequest request) {
        return new CreateProducerCommand(
                request.id(),
                request.name(),
                request.entityType(),
                request.typeActivity(),
                request.country(),
                request.city(),
                request.street(),
                request.postal()
        );
    }

    public static DeleteProducerCommand toDeleteCommand(final String id) {
        return new DeleteProducerCommand(id);
    }

    public static GetProducerByIdQuery toGetByIdQuery(final String id) {
        return new GetProducerByIdQuery(id);
    }

    public static ProducerResponse toResponse(final Producer producer) {
        return new ProducerResponse(
                producer.getId().value(),
                producer.getName().value(),
                producer.getEntityType().name(),
                producer.getTypeActivity().name(),
                producer.getCountry().value(),
                producer.getCity().value(),
                producer.getStreet().value(),
                producer.getPostal().value()
        );
    }

    public static List<ProducerResponse> toResponseList(final List<Producer> producers) {
        return producers.stream().map(ProducerDesktopMapper::toResponse).toList();
    }
}
