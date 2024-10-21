package com.geralab.JavaExam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "users_id_seq_", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq_")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    private Country country;

    @Column(name = "created_at")
    private OffsetDateTime createdAt ;

    public User(String firstName, Integer age, Country country) {
        this.firstName = firstName;
        this.age = age;
        this.country = country;
    }

    public User(String firstName, Integer age, Country country, OffsetDateTime createdAt) {
        this.firstName = firstName;
        this.age = age;
        this.country = country;
        this.createdAt = createdAt;
    }
}
