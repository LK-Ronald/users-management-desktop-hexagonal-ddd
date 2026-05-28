package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Producer;

public interface SaveProducerPort {
    Producer save(Producer producer);
}
