package com.br.mastertech.mashie.services;

import com.br.mastertech.mashie.models.Mashie;
import com.br.mastertech.mashie.models.Statement;
import com.br.mastertech.mashie.repositories.MashieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MashieService {
    @Autowired
    private MashieRepository mashieRepository;

    @Autowired
    private StatementService statementService;

    public Mashie create(Mashie mashie){
        List<Long> ids = new ArrayList<>();

//        mashie.getStatements().forEach(statement -> ids.add(statement.getId()));
        for (Statement statement : mashie.getStatements()){
            ids.add(statement.getId());
        }
        mashie.setStatements(statementService.loadStatementsById(ids));
        return mashieRepository.save(mashie);
    }

    public Optional<Mashie> load(String id){
        return mashieRepository.findById(id);

    }
}
