package com.anderson.application.adapter.http.controller;

import com.anderson.application.adapter.http.config.IController;
import com.anderson.application.adapter.http.dto.AccountCreateDTO;
import com.anderson.application.adapter.http.dto.AccountDTO;
import com.anderson.application.mapper.AccountMapper;
import com.anderson.common.excpetion.NotFoundCustomException;
import com.anderson.core.model.Account;
import com.anderson.core.port.input.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Slf4j
@Validated
@Api("API  Accounts")
@RestController
@RequestMapping(IController.CONTEXT_ACCOUNT)
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;


    @GetMapping("/{accountId}")
    @ApiOperation("Get account by id")
    public ResponseEntity<AccountDTO> getAccount(
            @Valid @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}",
                    message = "UUID Invalid") @PathVariable("accountId") String accountId) {

        Account account = accountService.findByID(UUID.fromString(accountId)).orElseThrow(NotFoundCustomException::new);

        return ResponseEntity.ok(AccountMapper.toAccountDTO(account));
    }

    @PostMapping
    @ApiOperation("Create a new account")
    public ResponseEntity<AccountDTO> postAccount(@Valid @RequestBody AccountCreateDTO accountCreateDTO) {

        Account account = accountService.save(AccountMapper.toAccount(accountCreateDTO));

        return ResponseEntity.ok(AccountMapper.toAccountDTO(account));
    }

}
