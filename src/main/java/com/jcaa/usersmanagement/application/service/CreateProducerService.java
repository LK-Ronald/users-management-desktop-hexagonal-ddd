package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateProducerUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveProducerPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateProducerCommand;
import com.jcaa.usersmanagement.application.service.mapper.ProducerApplicationMapper;
import com.jcaa.usersmanagement.domain.model.Producer;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class CreateProducerService implements CreateProducerUseCase {

    private final SaveProducerPort saveProducerPort;
    private final Validator validator;

    @Override
    public Producer execute(final CreateProducerCommand command) {
        validateCommand(command);

        final Producer producerToSave = ProducerApplicationMapper.fromCreateCommandToModel(command);
        final Producer savedProducer = saveProducerPort.save(producerToSave);

        return savedProducer;
    }

    private void validateCommand(final CreateProducerCommand command) {
        final Set<ConstraintViolation<CreateProducerCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
