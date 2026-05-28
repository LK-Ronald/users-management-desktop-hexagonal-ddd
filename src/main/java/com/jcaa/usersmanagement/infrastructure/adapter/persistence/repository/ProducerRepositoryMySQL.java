package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.*;
import com.jcaa.usersmanagement.domain.exception.ProducerNotFoundException;
import com.jcaa.usersmanagement.domain.model.Producer;
import com.jcaa.usersmanagement.domain.valueobject.ProducerId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ProducerPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.ProducerPersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.ProducerPersistenceMapper;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProducerRepositoryMySQL implements DeleteProducerPort, GetAllProducerPort, GetProducerByIdPort, SaveProducerPort {

    private static final String SQL_INSERT = "INSERT INTO producer (id, name, entity_type, type_activity, country, city, street, postal, created_at) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW()";

    private static final String SQL_SELECT_BY_ID = "SELECT id, name, entity_type, type_activity, country, city, street, postal, created_at" +
            " FROM producer" +
            " WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_ALL = "SELECT id, name, entity_type, type_activity, country, city, street, postal, created_at " +
            "FROM producer " +
            "ORDER BY name ASC";

    private static final String SQL_DELETE = "DELETE FROM producer WHERE id = ?";

    private final Connection connection;

    @Override
    public void delete(final ProducerId producerId) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setString(1, producerId.value());
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw ProducerPersistenceException.becauseDeleteFailed(producerId.value(), e);
        }
    }

    @Override
    public List<Producer> getAll() {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet resultSet = statement.executeQuery();
            return ProducerPersistenceMapper.fromResultSetToModelList(resultSet);
        } catch (final SQLException e) {
            throw ProducerPersistenceException.becauseFindAllFailed(e);
        }
    }

    @Override
    public Optional<Producer> getById(final ProducerId producerId) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setString(1, producerId.value());
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(ProducerPersistenceMapper.fromResultSetToModel(resultSet));
        } catch (final SQLException e) {
            throw ProducerPersistenceException.becauseFindByIdFailed(producerId.value(), e);
        }
    }

    @Override
    public Producer save(final Producer producer) {
        final ProducerPersistenceDto dto = ProducerPersistenceMapper.fromModelToDto(producer);
        executeSave(dto);
        return findByIdOrFail(producer.getId());
    }

    private void executeSave(final ProducerPersistenceDto dto) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, dto.id());
            statement.setString(2, dto.name());
            statement.setString(3, dto.EntityType());
            statement.setString(4, dto.TypeActivity());
            statement.setString(5, dto.country());
            statement.setString(6, dto.city());
            statement.setString(7, dto.street());
            statement.setString(8, dto.postal());
            statement.setString(9, dto.createdAt());
            statement.executeUpdate();

        } catch (final SQLException e) {
            throw ProducerPersistenceException.becauseSaveFailed(dto.id(), e);
        }
    }

    private Producer findByIdOrFail(final ProducerId producerId) {
        return getById(producerId).orElseThrow(() -> ProducerNotFoundException.becauseIdWasNotFound(producerId.value()));
    }
}
