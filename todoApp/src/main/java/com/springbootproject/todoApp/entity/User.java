package com.springbootproject.todoApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User { // This class defines a User entity with standard fields like id, name, username,
                    // email, password, and a many-to-many relationship with Role.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column( nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // defines a many-to-many relationship between User and Role entities
    // a user can have multiple roles and a role can be assigned to multiple users
   // @Join Table specifies the join table that manages the many-to-many relationship between User and Role
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

}
