package com.anderson.adapter.database.imp;


import com.anderson.adapter.database.entity.TransactionEntity;
import com.anderson.adapter.database.repository.TransactionRepositoryDB;
import com.anderson.core.model.Transaction;
import com.anderson.core.service.out.TransactionRepository;
import com.anderson.mapper.TransactionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionServiceRepositoryImp implements TransactionRepository {

    private TransactionRepositoryDB repositoryDB;

    @Override
    public Transaction save(Transaction transcation) {

        log.info("Save Transaction in DB");

        TransactionEntity transactionEntity = repositoryDB.save(TransactionMapper.toTransactionEntity(transcation));

        return TransactionMapper.toTransaction(transactionEntity);
    }

}
