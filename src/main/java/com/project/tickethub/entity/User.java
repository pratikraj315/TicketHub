package com.project.tickethub.entity;

import com.project.tickethub.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

//1. @Entity and @Table
//- @Entity tells Hibernate that this class should be mapped to a database table.
//        - @Table(name = "users") explicitly names the table users.
//Why not name it User? USER can conflict with reserved words in some databases. Using users avoids that potential problem.
//
//        2. @Id and @GeneratedValue
//@Id
//@GeneratedValue(strategy = GenerationType.IDENTITY)
//private Long id;
//
//
//MySQL generates the primary key automatically using its auto-increment feature.
//
//        3. @Enumerated(EnumType.STRING)
//
//This stores USER or ADMIN as text in MySQL instead of storing their numeric positions.
//
//        Why? If we later reorder enum constants, existing database values won't accidentally change their meaning.
//
//        4. Lombok annotations
//- @Getter: Generates getter methods.
//- @Setter: Generates setter methods.
//- @NoArgsConstructor: Generates a no-argument constructor.
//        - AccessLevel.PROTECTED: Restricts that constructor to protected access.
//JPA needs a no-argument constructor. We use protected access because we don't want application code casually creating incomplete entities.
//
//We intentionally avoid @Data on this entity because its generated equals(), hashCode() and toString() can cause problems with JPA entities and relationships.
//
//        5. @PrePersist
//
//This method executes automatically before Hibernate inserts a new user record. It sets the account creation time without requiring the controller to supply it.