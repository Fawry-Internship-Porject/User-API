package com.example.user_api.data;

import com.example.user_api.model.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String title;
    private String role;
    private String mail;
    private String password;
    private String phone;
    private float salaryGross;

    @Enumerated(EnumType.STRING)
    private Level level;
    @Transient
    private float salaryNet;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    private float calculateNetSalary(float grossSalary) {

        return grossSalary * 0.8f;
    }
}
