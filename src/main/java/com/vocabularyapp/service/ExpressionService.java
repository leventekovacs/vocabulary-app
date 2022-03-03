package com.vocabularyapp.service;

import com.vocabularyapp.model.Expression;
import com.vocabularyapp.repository.ExpressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpressionService {

    private ExpressionRepository expressionRepository;

    @Autowired
    public void setExpressionRepository(ExpressionRepository expressionRepository) {
        this.expressionRepository = expressionRepository;
    }

    public List<Expression> getAllExpressions() {
        return expressionRepository.findAll();
    }
}
