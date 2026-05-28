package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Producer;
import com.jcaa.usersmanagement.domain.valueobject.ProducerId;

import java.util.Optional;

public interface GetProducerByIdPort {

    Optional<Producer> getById(ProducerId producerId);
}
