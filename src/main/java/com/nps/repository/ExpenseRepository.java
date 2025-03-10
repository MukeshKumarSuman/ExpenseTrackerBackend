package com.nps.repository;

import com.nps.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Serializable> {
}
