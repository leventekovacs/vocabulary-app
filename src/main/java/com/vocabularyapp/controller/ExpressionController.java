package com.vocabularyapp.controller;

import com.vocabularyapp.model.Expression;
import com.vocabularyapp.service.ExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expression")
public class ExpressionController {

    @Autowired private ExpressionService expressionService;

    //Test
    @GetMapping()
    public List<Expression> getAllExpressions() {
        return  expressionService.getAllExpressions();
    }

}
