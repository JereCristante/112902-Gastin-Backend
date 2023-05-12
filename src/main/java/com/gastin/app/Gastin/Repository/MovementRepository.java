package com.gastin.app.Gastin.Repository;

import com.gastin.app.Gastin.Model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement,Long> {
}
