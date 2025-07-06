package com.openschool.infrastructure.adapter.out.persistence.identity.repository;

import com.openschool.domain.identity.model.UserCredentials;
import com.openschool.infrastructure.adapter.out.persistence.identity.entity.UserCredentialsEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public class UserCredentialsRepository implements com.openschool.identity.port.out.UserCredentialsRepository {

    private final JpaUserCredentialsRepository jpaUserCredentialsRepository;

    public UserCredentialsRepository(JpaUserCredentialsRepository jpaUserCredentialsRepository) {
        this.jpaUserCredentialsRepository = jpaUserCredentialsRepository;
    }

    @Override
    public Optional<UserCredentials> findByUsername(String username) {
        return jpaUserCredentialsRepository.findByUsername(username)
                .map(this::toDomain);
    }

    @Override
    public void save(UserCredentials userCredentials) {
        UserCredentialsEntity entity = new UserCredentialsEntity();
        entity.setId(UUID.fromString(userCredentials.getId()));
        entity.setUsername(userCredentials.getUsername());
        entity.setPasswordHash(userCredentials.getPasswordHash());
        jpaUserCredentialsRepository.save(entity);
    }

    private UserCredentials toDomain(UserCredentialsEntity entity) {
        return new UserCredentials(
                entity.getId().toString(),
                entity.getUsername(),
                entity.getPasswordHash()
        );
    }
}
