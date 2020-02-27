package com.br.mastertech.mashie.controllers;

import com.br.mastertech.mashie.models.Statement;
import com.br.mastertech.mashie.services.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statement")
public class StatementController {
    @Autowired
    private StatementService statementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Statement create(@RequestBody Statement statement){
        return statementService.create(statement);
    }
}
