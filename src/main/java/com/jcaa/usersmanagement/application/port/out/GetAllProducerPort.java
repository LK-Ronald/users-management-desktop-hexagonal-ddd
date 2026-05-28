package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Producer;

import java.util.List;

public interface GetAllProducerPort {
    List<Producer> getAll();
}
