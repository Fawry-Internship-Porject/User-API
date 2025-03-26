package com.example.user_api.data;

import com.example.user_api.controller.Level;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.Objects;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotAudited
    private int id;
    @NotAudited
    private String name;
    private String title;
    private String role;
    @NotAudited
    private String mail;
    @NotAudited
    private String password;
    private String phone;
    private Float salaryGross;

    @Enumerated(EnumType.STRING)
    private Level level;
    @Transient
    private Float salaryNet;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;


    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    private Float calculateNetSalary(Float grossSalary) {

        return grossSalary * 0.8f;
    }

    public Float getSalaryNet() {
        return calculateNetSalary(salaryGross);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id== user.id;
    }
}
