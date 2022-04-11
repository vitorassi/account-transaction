package com.anderson.core.service.imput;


import com.anderson.core.port.input.AccountService;
import com.anderson.core.service.out.AccountRepository;
import com.anderson.core.service.validation.imp.ValidateAccountDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceImpTest {

    @Mock
    private AccountRepository accountRepository;

    Validation validation;

    private AccountService accountService;

    @BeforeEach
    public void init(){
        accountService = new AccountServiceImp(accountRepository,DASDSA);
    }


}