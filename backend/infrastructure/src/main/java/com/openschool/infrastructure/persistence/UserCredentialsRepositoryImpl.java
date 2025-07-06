package com.openschool.infrastructure.persistence;

import com.openschool.domain.identity.model.UserCredentials;
import com.openschool.identity.port.out.UserCredentialsRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public class UserCredentialsRepositoryImpl implements UserCredentialsRepository {

    private final SpringDataUserCredentialsRepository springRepo;

    public UserCredentialsRepositoryImpl(SpringDataUserCredentialsRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public Optional<UserCredentials> findByUsername(String username) {
        return springRepo.findByUsername(username)
                .map(this::toDomain);
    }

    @Override
    public void save(UserCredentials userCredentials) {
        UserCredentialsEntity entity = new UserCredentialsEntity();
        entity.setId(UUID.fromString(userCredentials.getId()));
        entity.setUsername(userCredentials.getUsername());
        entity.setPasswordHash(userCredentials.getPasswordHash());
        springRepo.save(entity);
    }

    private UserCredentials toDomain(UserCredentialsEntity entity) {
        return new UserCredentials(
                entity.getId().toString(),
                entity.getUsername(),
                entity.getPasswordHash()
        );
    }
}
