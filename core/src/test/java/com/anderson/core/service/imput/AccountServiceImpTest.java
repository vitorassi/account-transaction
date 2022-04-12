package com.anderson.core.service.imput;


import com.anderson.core.model.Account;
import com.anderson.core.port.input.AccountService;
import com.anderson.core.service.out.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.anderson.core.service.internal.Validation;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        verify(validation, times(1)).validate(any(Account.class));
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void testfindByID(){
        accountService.findByID(UUID.randomUUID().toString());
        verify(accountRepository, times(1)).findBy(any(UUID.class));
    }

}