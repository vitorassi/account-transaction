package com.anderson.adapter.database.repository;

import com.anderson.adapter.database.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepositoryDB extends JpaRepository<TransactionEntity, UUID> {

}
