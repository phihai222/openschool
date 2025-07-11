package com.openschool.infrastructure.adapter.out.persistence.identity.repository.jpa;

import com.openschool.infrastructure.adapter.out.persistence.identity.entity.AccountRoleEntity;
import com.openschool.infrastructure.adapter.out.persistence.identity.entity.AccountRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAccountRoleRepository extends JpaRepository<AccountRoleEntity, AccountRoleId> {
    @Query("SELECT CASE WHEN COUNT(ar) > 0 THEN true ELSE false END " +
            "FROM AccountRoleEntity ar " +
            "WHERE ar.role.name = :roleName")
    boolean existsByRoleName(@Param("roleName") String roleName);
}
