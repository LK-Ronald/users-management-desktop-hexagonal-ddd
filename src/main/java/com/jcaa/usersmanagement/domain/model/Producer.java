package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.ProducerEntityType;
import com.jcaa.usersmanagement.domain.enums.ProducerTypeActivity;
import com.jcaa.usersmanagement.domain.valueobject.*;
import lombok.Value;

@Value
public class Producer {

    ProducerId id;
    ProducerName name;
    ProducerEntityType EntityType;
    ProducerTypeActivity TypeActivity;
    ProducerCountryName country;
    ProducerCityName city;
    ProducerStreetNumber street;
    ProducerPostalNumber postal;

    public static Producer create(
            final ProducerId id,
            final ProducerName name,
            final ProducerEntityType EntityType,
            final ProducerTypeActivity TypeActivity,
            final ProducerCountryName country,
            final ProducerCityName city,
            final ProducerStreetNumber street,
            final ProducerPostalNumber postal) {
        return new Producer(id, name, EntityType, TypeActivity, country, city, street, postal);
    }
}
