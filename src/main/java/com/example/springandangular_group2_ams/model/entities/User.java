package com.example.springandangular_group2_ams.model.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "AppUser")
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;
}
