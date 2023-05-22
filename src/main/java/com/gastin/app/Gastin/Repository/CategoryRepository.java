package com.gastin.app.Gastin.Repository;

import com.gastin.app.Gastin.DTO.AccountMovementDTO;
import com.gastin.app.Gastin.DTO.ListMovementsDTO;
import com.gastin.app.Gastin.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    public List<Category> findByUserId(Long userId);

}
