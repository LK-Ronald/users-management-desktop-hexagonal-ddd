package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateProducerCommand;
import com.jcaa.usersmanagement.domain.model.Producer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateProducerUseCase {

    Producer execute(@NotNull @Valid CreateProducerCommand command);
}
