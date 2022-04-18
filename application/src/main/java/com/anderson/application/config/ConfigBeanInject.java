package com.anderson.application.config;

import com.anderson.config.ConfigInfrastructureBean;
import com.anderson.core.port.input.AccountService;
import com.anderson.core.port.input.TransactionService;
import com.anderson.core.port.internal.CalculateTransactionService;
import com.anderson.core.service.imput.AccountServiceImp;
import com.anderson.core.service.imput.TransactionServiceImp;
import com.anderson.core.service.internal.CalculateTransactionServiceImp;
import com.anderson.core.service.internal.Validation;
import com.anderson.core.service.out.AccountRepository;
import com.anderson.core.service.out.TransactionRepository;
import com.anderson.core.service.validation.ValidationAccountServiceImp;
import com.anderson.core.service.validation.ValidationTransactionServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {ConfigInfrastructureBean.class})
public class ConfigBeanInject {


    @Bean
    public Validation validationAccountService(AccountRepository accountRepository) {
        return new ValidationAccountServiceImp(accountRepository);
    }

    @Bean
    public AccountService accountService(AccountRepository accountRepository, Validation validationAccountService) {
        return new AccountServiceImp(accountRepository, validationAccountService);
    }

    @Bean
    public Validation validationTransactionService(AccountRepository accountRepository) {
        return new ValidationTransactionServiceImp(accountRepository);
    }

    @Bean
    public CalculateTransactionService calculateTransactionService() {
        return new CalculateTransactionServiceImp();
    }

    @Bean
    public TransactionService transactionService(Validation validationTransactionService,
                                                 TransactionRepository transactionRepository,
                                                 CalculateTransactionService calculateTransactionService,
                                                 AccountRepository accountRepository) {
        return new TransactionServiceImp(transactionRepository,
                validationTransactionService,
                calculateTransactionService,
                accountRepository);
    }


}
