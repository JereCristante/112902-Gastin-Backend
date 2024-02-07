package com.gastin.app.Gastin.Repository;

import com.gastin.app.Gastin.DTO.TransactionsMetricDTO;
import com.gastin.app.Gastin.DTO.UsersCreatedDTO;
import com.gastin.app.Gastin.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findOneByEmail(String email);
    boolean existsByEmail(String email);
    Optional<List<User>> findAllByidUpperUser(Long upperUser);

    @Query(nativeQuery = true,name = "User.UserCreatedMetrics")
    List<UsersCreatedDTO> getUsersCreatedMetric();
}
