package com.gastin.app.Gastin.Repository;

import com.gastin.app.Gastin.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    public List<Category> findByUserId(Long userId);
}
