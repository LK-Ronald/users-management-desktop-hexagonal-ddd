package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.ProducerEntityType;
import com.jcaa.usersmanagement.domain.enums.ProducerTypeActivity;
import com.jcaa.usersmanagement.domain.model.Producer;
import com.jcaa.usersmanagement.domain.valueobject.*;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ProducerPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.ProducerEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ProducerPersistenceMapper {

    public ProducerPersistenceDto fromModelToDto(final Producer producer) {
        return new ProducerPersistenceDto(
                producer.getId().value(),
                producer.getName().value(),
                producer.getEntityType().name(),
                producer.getTypeActivity().name(),
                producer.getCountry().value(),
                producer.getCity().value(),
                producer.getStreet().value(),
                producer.getPostal().value(),
                null
        );
    }

    public ProducerEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
        return new ProducerEntity(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getString("entity_type"),
                resultSet.getString("type_activity"),
                resultSet.getString("country"),
                resultSet.getString("city"),
                resultSet.getString("street"),
                resultSet.getString("postal"),
                resultSet.getString("created_at"));
    }

    public Producer fromEntityToModel(final ProducerEntity entity) {
        return new Producer(
                new ProducerId(entity.id()),
                new ProducerName(entity.name()),
                ProducerEntityType.fromString(entity.EntityType()),
                ProducerTypeActivity.fromString(entity.TypeActivity()),
                new ProducerCountryName(entity.country()),
                new ProducerCityName(entity.city()),
                new ProducerStreetNumber(entity.street()),
                new ProducerPostalNumber(entity.postal())
        );
    }

    public Producer fromResultSetToModel(final ResultSet resultSet) throws SQLException {
        return fromEntityToModel(fromResultSetToEntity(resultSet));
    }

    public List<Producer> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
        final List<Producer> producers = new ArrayList<>();
        while (resultSet.next()) {
            producers.add(fromResultSetToModel(resultSet));
        }
        return producers;
    }
}
