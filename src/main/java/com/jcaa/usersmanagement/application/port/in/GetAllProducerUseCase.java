package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Producer;

import java.util.List;

public interface GetAllProducerUseCase {

    List<Producer> execute();
}
