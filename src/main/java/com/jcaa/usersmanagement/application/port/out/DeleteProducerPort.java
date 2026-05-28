package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.ProducerId;

public interface DeleteProducerPort {
    void delete(ProducerId producerId);
}
