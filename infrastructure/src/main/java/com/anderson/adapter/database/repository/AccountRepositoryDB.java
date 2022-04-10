package com.anderson.adapter.database.repository;

import com.anderson.adapter.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepositoryDB extends JpaRepository<AccountEntity, UUID> {

    Optional<AccountEntity> findByDocumentNumber(String documentNumber);

}
