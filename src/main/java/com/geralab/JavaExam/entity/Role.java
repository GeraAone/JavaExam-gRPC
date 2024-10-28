package com.geralab.JavaExam.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "roles")
@Data
@SuperBuilder
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "roles_id_seq_", sequenceName = "roles_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq_")
    private Long id;

    @Column(name = "role_name")
    private String roleName;
}
