package com.anderson.core.service.imput;


import com.anderson.core.model.Account;
import com.anderson.core.port.input.AccountService;
import com.anderson.core.service.out.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.anderson.core.service.internal.Validation;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AccountServiceImpTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private Validation validation;

    private AccountService accountService;

    @BeforeEach
    public void init(){
        accountService = new AccountServiceImp(accountRepository, validation);
    }

    @Test
    public void testSave(){

        accountService.save(Account.builder().documentNumber("1234").build());
        Mockito.verify(accountRepository.save(any()), Mockito.times(1));


    }


}