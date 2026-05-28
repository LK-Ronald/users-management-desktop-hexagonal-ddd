package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllProducerUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllProducerPort;
import com.jcaa.usersmanagement.domain.model.Producer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllProducerService implements GetAllProducerUseCase {

    private final GetAllProducerPort getAllProducerPort;

    @Override
    public List<Producer> execute() {
        return getAllProducerPort.getAll();
    }
}
