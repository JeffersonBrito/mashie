package com.br.mastertech.mashie.services;

import com.br.mastertech.mashie.models.Mashie;
import com.br.mastertech.mashie.models.Statement;
import com.br.mastertech.mashie.repositories.MashieRepository;
import com.br.mastertech.mashie.repositories.StatementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MashieServiceTest {
    @Autowired
    MashieService mashieService;

    @MockBean
    MashieRepository mashieRepository;

    @MockBean
            StatementService statementService;

    List<Statement> statements;

    @BeforeEach
    public void prepare(){
        Statement statement = new Statement();
        statement.setId(1);
        statement.setText("Prefiro ir para pqp");
        statements = new ArrayList<>();
        statements.add(statement);
    }

    @Test
    public void shouldCreateMashie(){
        //given

        Mashie mashie = new Mashie();
        mashie.setId("citi");
        mashie.setStatements(statements);

        Mockito.when(statementService.loadStatementsById(Mockito.anyList())).thenReturn(statements);
        Mockito.when(mashieRepository.save(mashie)).thenReturn(mashie);

        //when
        Mashie createMashie = mashieService.create(mashie);

        //then
        Assertions.assertNotNull(createMashie);
        Assertions.assertEquals(1, mashie.getStatements().size());
    }

    @Test
    public void shouldLoadMashieById(){
        //given
        Mashie mashie = new Mashie();
        Mockito.when(mashieRepository.findById(mashie.getId())).thenReturn(Optional.of(mashie));

        //when
        Optional<Mashie> mashieOptional = mashieService.load(mashie.getId());

        //then

        Assertions.assertTrue(mashieOptional.isPresent());
        Assertions.assertEquals(mashie, mashieOptional.get());
    }
}
