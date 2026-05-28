package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetProducerByIdQuery;
import com.jcaa.usersmanagement.domain.model.Producer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetProducerByIdUseCase {
    Producer execute(@NotNull @Valid GetProducerByIdQuery query);
}
