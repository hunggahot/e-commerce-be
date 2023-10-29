package com.example.ecommercebe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private String description;

        @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
        @CollectionTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"))
        @Enumerated(EnumType.STRING)
        private Set<Permission> permissions = EnumSet.noneOf(Permission.class);
}