package com.anderson.core.service.imput;

import com.anderson.core.model.Account;
import com.anderson.core.model.Operation;
import com.anderson.core.model.Transaction;
import com.anderson.core.port.input.TransactionService;
import com.anderson.core.port.internal.CalculateTransactionService;
import com.anderson.core.service.internal.Validation;
import com.anderson.core.service.out.AccountRepository;
import com.anderson.core.service.out.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class TransactionServiceImp implements TransactionService {

    private TransactionRepository repository;

    private Validation validationTransactionService;

    private CalculateTransactionService calculateTransactionService;


    private AccountRepository accountRepository;

    @Override
    public Transaction save(Transaction transaction) {

        log.info("Save Transaction");
        //validate transaction
        validationTransactionService.validate(transaction);

        //calculate amount
        calculateTransactionService.calculate(transaction);

        // default date now
        transaction.setEventDate(LocalDateTime.now());

        Transaction transcationSave = repository.save(transaction);

        //update limit credit
        updateLimite(transcationSave);

        return transcationSave;
    }

    private void updateLimite(Transaction transaction) {

        Account account = accountRepository.findBy(transaction.getAccountId()).get();

        account.setAvailableCreditLimit(account.getAvailableCreditLimit().add(transaction.getAmount()));

        accountRepository.save(account);
    }


}
