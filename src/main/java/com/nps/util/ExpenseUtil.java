package com.nps.util;

import com.nps.dto.Expense;
import com.nps.entity.ExpenseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ExpenseUtil {
    public static Expense toExpenseDto(ExpenseEntity e) {
        return new Expense(e.getId(), e.getDate(), e.getAmount(), e.getDescription());
    }

    public static void updateExpenseEntityFromExpense(Expense expense, ExpenseEntity expenseEntity) {
        if (expense.getDate() != null) {
            expenseEntity.setDate(expense.getDate());
        }
        if (expense.getDescription() != null) {
            expenseEntity.setDescription(expense.getDescription());
        }
        if (expense.getAmount() != null) {
            expenseEntity.setAmount(expense.getAmount());
        }
    }

    public static ExpenseEntity toExpenseEntity(Expense expense) {
        return new ExpenseEntity(expense.getDate(), expense.getAmount(), expense.getDescription());
    }

    public static List<Expense> toExpenseDtoList(List<ExpenseEntity> list) {
        return list.stream().map(e -> toExpenseDto(e)).collect(Collectors.toList());
    }
}
