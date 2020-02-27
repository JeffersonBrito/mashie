package com.br.mastertech.mashie.services;

import com.br.mastertech.mashie.models.Statement;
import com.br.mastertech.mashie.repositories.StatementRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StatementServiceTest {
    @Autowired
    StatementService statementService;

    @MockBean
    StatementRepository statementRepository;

    @Test
    public void shouldCreateAStatement(){
        //giiven
        String text = "Prefiro tomar café";
        Statement statement = new Statement();
        statement.setText(text);

//        Statement returnedStatement = new Statement();
//        returnedStatement.setText(statement.getText());
//        returnedStatement.setId(1);

        Mockito.when(statementRepository.save(statement)).thenReturn(statement);

//        Mockito.when(statementRepository.save(statement)).then(invocationOnMock -> {
//            Statement arg = (Statement) invocationOnMock.getArgument(0);
//            arg.setId(1);
//            return arg;
//        });

        //when
        Statement savedStatement = statementService.create(statement);

        //then
        Assertions.assertNotNull(savedStatement);
        Assertions.assertEquals(text, savedStatement.getText());
    }

    @Test
    public void shouldLoadStatementById(){
        //given
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);

        Statement statement1 = new Statement();
        Statement statement2 = new Statement();

        List<Statement> foundStatements = new ArrayList<>();
        foundStatements.add(statement1);
        foundStatements.add(statement2);


        Mockito.when(statementRepository.findAllById(ids)).thenReturn(foundStatements);

        //when
        Iterable<Statement> statements = statementService.loadStatementsById(ids);


        //then
        List<Statement> statementsList = new ArrayList<>();
        statements.forEach(statementsList::add);

        Assertions.assertEquals(2, statementsList.size());
    }
}
