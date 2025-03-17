package com.example.user_api.data;

import com.example.user_api.model.Level;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int managerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;

    @Column(nullable = false)
    private String name;

    private String title;
    private String role;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String phone;
    private String company;
    private String department;
    private String location;
    @Transient
    private float salaryNet;
    private float salaryGross;

    public User(int managerId, Level level, String name, String title, String role,
                String email, String password, String phone, String company,
                String department, String location, float salaryGross) {
        this.managerId = managerId;
        this.level = level;
        this.name = name;
        this.title = title;
        this.role = role;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.company = company;
        this.department = department;
        this.location = location;
        this.salaryGross = salaryGross;
    }

    private float calculateNetSalary(float grossSalary) {
        return grossSalary * 0.8f;
    }
}
