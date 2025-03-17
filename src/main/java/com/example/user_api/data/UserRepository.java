package com.example.user_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.id = :userId")
    List<User> getUserHistory(@Param("userId") int userId);
    @Query("SELECT u.name as name, u.email as email, u.role as role FROM User u WHERE u.id = :id")
    Optional<UserProjection> findUserById(@Param("id") int id);



}
