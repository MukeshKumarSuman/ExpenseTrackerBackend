package com.nps.controller;

import com.nps.dto.Expense;
import com.nps.exception.ExpenseNotFoundException;
import com.nps.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expensetracker")
public class ExpenseTrackerController {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseTrackerController.class);
    private final ExpenseService expenseService;

    public ExpenseTrackerController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getExpenses() {
        logger.info("get the request for all expenses");
        List<Expense> expenses = expenseService.getExpenses();
        logger.info("Returning get response:{}", expenses);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        logger.info("get the request for add expenses:{}", expense);
        Expense savedExpense = expenseService.addExpense(expense);
        logger.info("Returning add response:{}", savedExpense);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense) throws ExpenseNotFoundException {
        logger.info("get the request for update expenses: {}", expense);
        Expense updateExpense = expenseService.updateExpense(expense);
        logger.info("Returning update response:{}", updateExpense);
        return new ResponseEntity<>(updateExpense, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Integer id) throws ExpenseNotFoundException {
        logger.info("Get the request to delete expenses with id: {}", id);
        expenseService.deleteExpense(id);
        logger.info("Delete successfully with id:{}", id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
