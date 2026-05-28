package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetProducerByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetProducerByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetProducerByIdQuery;
import com.jcaa.usersmanagement.application.service.mapper.ProducerApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ProducerNotFoundException;
import com.jcaa.usersmanagement.domain.model.Producer;
import com.jcaa.usersmanagement.domain.valueobject.ProducerId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class GetProducerByIdService implements GetProducerByIdUseCase {

    private final GetProducerByIdPort getProducerByIdPort;
    private final Validator validator;

    @Override
    public Producer execute(final GetProducerByIdQuery query) {
        validateQuery(query);

        final ProducerId producerId = ProducerApplicationMapper.fromGetUserByIdQueryToProducerId(query);
        return getProducerByIdPort.getById(producerId).orElseThrow(() -> ProducerNotFoundException.becauseIdWasNotFound(producerId.value()));
    }

    private void validateQuery(final GetProducerByIdQuery query) {
        final Set<ConstraintViolation<GetProducerByIdQuery>> violations = validator.validate(query);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
