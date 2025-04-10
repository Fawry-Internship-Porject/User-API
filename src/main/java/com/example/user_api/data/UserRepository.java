package com.example.user_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByMail(String mail);

    @Query("SELECT u.id AS id, u.name AS name, u.title AS title, u.role AS role, " +
            "u.mail AS mail, u.phone AS phone, u.level AS level, " +
            "u.manager.id AS managerId, u.department.id AS departmentId " +
            "FROM User u WHERE u.id = :id")
    Optional<UserProjection> findUserById(@Param("id") int id);

}
