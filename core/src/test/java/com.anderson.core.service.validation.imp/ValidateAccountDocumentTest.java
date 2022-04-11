package com.anderson.core.service.validation.imp;


import com.anderson.common.excpetion.BadRequestCustomException;
import com.anderson.common.excpetion.ConflictCustomException;
import com.anderson.core.model.Account;
import com.anderson.core.service.out.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateAccountDocumentTest {

    @Mock
    private AccountRepository accountRepository;

    private ValidateAccountDocument validateAccountDocument;

    @BeforeEach
    public void init(){
       validateAccountDocument = new ValidateAccountDocument(accountRepository);
    }


    @Test
    @DisplayName("Test Account Number Success")
    public void testAccountNumberSuccess(){
        try {
            Account account = Account.builder().id(UUID.randomUUID())
                    .documentNumber("1234").build();

            validateAccountDocument.validate(account);

        }catch (Exception e){
            e.printStackTrace();
            fail("Fail test");
        }
    }

    @Test
    @DisplayName("Test invalid account number")
    public void testInvalidAccountNumber(){
        try {
            Account account = Account.builder().id(UUID.randomUUID())
                    .documentNumber("invalid").build();
            validateAccountDocument.validate(account);

            fail("Fail test");
        }catch (BadRequestCustomException e){
            Assertions.assertEquals("Invalid number document", e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            fail("Fail test");
        }
    }

    @Test
    @DisplayName("Test duplicate number account")
    public void testDuplicateNumberAccount(){
        try {
            when(accountRepository.findByDocument("1234"))
                    .thenReturn(Optional.of(Account.builder()
                                    .id(UUID.randomUUID())
                            .documentNumber("1234").build()));

            Account account = Account.builder().id(UUID.randomUUID())
                    .documentNumber("1234").build();

            validateAccountDocument.validate(account);

            fail("Fail test");
        }catch (ConflictCustomException e){
            Assertions.assertEquals("Conflict \"Account\" exist in database with document number informed", e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            fail("Fail test");
        }
    }

}