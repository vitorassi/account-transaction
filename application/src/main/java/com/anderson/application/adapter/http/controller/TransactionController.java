package com.anderson.application.adapter.http.controller;

import com.anderson.application.adapter.http.config.IController;
import com.anderson.application.adapter.http.dto.TransactionCreateDTO;
import com.anderson.application.adapter.http.dto.TransactionDTO;
import com.anderson.application.mapper.TransactionMapper;
import com.anderson.core.model.Transaction;
import com.anderson.core.port.input.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@Api("API  Accounts")
@RestController
@RequestMapping(IController.CONTEXT_TRANSACTION)
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping
    @ApiOperation("Create a new transaction")
    public ResponseEntity<TransactionDTO> postTransaction(@Valid @RequestBody TransactionCreateDTO transactionDTO) {

        Transaction transaction = transactionService.save(TransactionMapper.toTransaction(transactionDTO));

        return ResponseEntity.ok(TransactionMapper.transactionDTO(transaction));
    }

}
