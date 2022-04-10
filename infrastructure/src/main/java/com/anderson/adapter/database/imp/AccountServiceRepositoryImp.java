package com.anderson.adapter.database.imp;

import com.anderson.adapter.database.entity.AccountEntity;
import com.anderson.adapter.database.repository.AccountRepositoryDB;
import com.anderson.core.model.Account;
import com.anderson.core.service.out.AccountRepository;
import com.anderson.mapper.AccountMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceRepositoryImp implements AccountRepository {


    private AccountRepositoryDB repository;

    @Override
    public Account save(Account account) {

        log.info("Save Account in DB");
        AccountEntity accountEntity = repository.save(AccountMapper.toAccountEntity(account));

        return AccountMapper.toAccount(accountEntity);
    }

    @Override
    public Optional<Account> findBy(UUID uuid) {
        return repository.findById(uuid)
                .map(accountEntity -> AccountMapper.toAccount(accountEntity));
    }

    @Override
    public Optional<Account> findByDocument(String documentNumber) {
        return repository.findByDocumentNumber(documentNumber)
                .map(accountEntity -> AccountMapper.toAccount(accountEntity));
    }
}

