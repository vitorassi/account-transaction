package com.anderson.core.service.validation.imp;

import com.anderson.common.excpetion.BadRequestCustomException;
import com.anderson.common.excpetion.ConflictCustomException;
import com.anderson.core.model.Account;
import com.anderson.core.service.internal.ValidateAccount;
import com.anderson.core.service.out.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class ValidateAccountDocument implements ValidateAccount {

    private AccountRepository repository;

    @Override
    public void validate(Account account) {

        validDocumentNumber(account);

        validIfExist(account);
    }

    private void validIfExist(Account account) {
        log.info("Document account validate document exist");
        repository.findByDocument(account.getDocumentNumber()).ifPresent(
                document -> {
                    throw new ConflictCustomException("Conflict \"Account\" exist in database with document number informed");
                }
        );
    }

    private void validDocumentNumber(Account account) {
        log.info("Document account validate document");
        Optional.ofNullable(account.getDocumentNumber())
                .filter(document -> document != null && !document.equals(""))
                .filter(document -> document.matches("[0-9]+"))
                .orElseThrow(() ->
                        new BadRequestCustomException("Invalid number document ")
                );
    }
}



