package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteProducerUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteProducerPort;
import com.jcaa.usersmanagement.application.port.out.GetProducerByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteProducerCommand;
import com.jcaa.usersmanagement.application.service.mapper.ProducerApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ProducerNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.ProducerId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class DeleteProducerService implements DeleteProducerUseCase {

    private final DeleteProducerPort deleteProducerPort;
    private final GetProducerByIdPort getProducerByIdPort;
    private final Validator validator;

    @Override
    public void execute(final DeleteProducerCommand command) {
        validateCommand(command);

        final ProducerId producerId = ProducerApplicationMapper.fromDeleteCommandToProducerId(command);
        ensureProducerExists(producerId);

        deleteProducerPort.delete(producerId);
    }

    private void validateCommand(final DeleteProducerCommand command) {
        final Set<ConstraintViolation<DeleteProducerCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureProducerExists(final ProducerId producerId) {
        getProducerByIdPort.getById(producerId).orElseThrow(() -> ProducerNotFoundException.becauseIdWasNotFound(producerId.value()));
    }
}
