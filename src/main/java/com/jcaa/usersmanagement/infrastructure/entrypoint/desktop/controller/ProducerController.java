package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateProducerUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteProducerUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllProducerUseCase;
import com.jcaa.usersmanagement.application.port.in.GetProducerByIdUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateProducerRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProducerResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.ProducerDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProducerController {

    private final CreateProducerUseCase createProducerUseCase;
    private final DeleteProducerUseCase deleteProducerUseCase;
    private final GetProducerByIdUseCase getProducerByIdUseCase;
    private final GetAllProducerUseCase getAllProducerUseCase;

    public List<ProducerResponse> listAllProducers() {
        final var producers = getAllProducerUseCase.execute();
        return ProducerDesktopMapper.toResponseList(producers);
    }

    public ProducerResponse findProducerById(final String id) {
        final var query = ProducerDesktopMapper.toGetByIdQuery(id);
        final var producer = getProducerByIdUseCase.execute(query);
        return ProducerDesktopMapper.toResponse(producer);
    }

    public ProducerResponse createProducer(final CreateProducerRequest request) {
        final var command = ProducerDesktopMapper.toCreateCommand(request);
        final var producer = createProducerUseCase.execute(command);
        return ProducerDesktopMapper.toResponse(producer);
    }

    public void deleteProducer(final String id) {
        final var command = ProducerDesktopMapper.toDeleteCommand(id);
        deleteProducerUseCase.execute(command);
    }
}
