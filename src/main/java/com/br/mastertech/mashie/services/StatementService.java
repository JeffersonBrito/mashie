package com.br.mastertech.mashie.services;

import com.br.mastertech.mashie.models.Statement;
import com.br.mastertech.mashie.repositories.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatementService {
    @Autowired
    private StatementRepository statementRepository;

    public Statement create(Statement statement) {
        return statementRepository.save(statement);
    }
    public List<Statement> loadStatementsById(Iterable<Long> ids) {
        Iterable<Statement> statements = statementRepository.findAllById(ids);

        List<Statement> statementList = new ArrayList<>();

        for (Statement statement : statements){
            statementList.add(statement);
        }

        return statementList;
    }
}
