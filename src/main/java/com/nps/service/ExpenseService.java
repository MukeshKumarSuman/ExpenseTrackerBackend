package com.nps.service;

import com.nps.dto.Expense;
import com.nps.entity.ExpenseEntity;
import com.nps.exception.ExpenseNotFoundException;
import com.nps.repository.ExpenseRepository;
import com.nps.util.ExpenseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseService.class);
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getExpenses() {
        logger.info("Fetching all expense from DB");
        List<ExpenseEntity> expenseList = expenseRepository.findAll();
        logger.info("Fetched all expense: {}", expenseList);
        return ExpenseUtil.toExpenseDtoList(expenseList);
    }

    public Expense addExpense(Expense expense) {
        logger.info("Adding expense to DB: {}", expense);
        ExpenseEntity savedExpense = expenseRepository.saveAndFlush(ExpenseUtil.toExpenseEntity(expense));
        logger.info("Added expense to DB: {}", savedExpense);
        return ExpenseUtil.toExpenseDto(savedExpense);
    }

    public Expense updateExpense(Expense expense) throws ExpenseNotFoundException {
        logger.info("Updating expense to DB: {}", expense);
        Optional<ExpenseEntity> optionalExpense = expenseRepository.findById(expense.getId());
        if (!optionalExpense.isPresent()) {
            logger.error("No such expense with id: {}", expense.getId());
            throw new ExpenseNotFoundException("No such expense with id:" + expense.getId());
        }
        ExpenseEntity expenseEntity = optionalExpense.get();
        ExpenseUtil.updateExpenseEntityFromExpense(expense, expenseEntity);
        ExpenseEntity savedExpense = expenseRepository.saveAndFlush(expenseEntity);
        logger.info("Updated expense to DB: {}", savedExpense);
        return ExpenseUtil.toExpenseDto(savedExpense);
    }

    public void deleteExpense(Integer id) {
        logger.info("Deleting expense to DB with id: {}", id);
        expenseRepository.deleteById(id);
        logger.info("expense with id:{} deleted successfully", id);
    }
}
