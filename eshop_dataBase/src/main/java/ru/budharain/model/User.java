package ru.budharain.model;

import javax.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String userName;
    @Column
    private String mail;
    @Column(nullable = false)
    private String password;
    @Column
    private BigDecimal balance;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @Column
    @OneToMany(mappedBy = "users",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Company> companies =new ArrayList<>();

    public User(Long id, String userName, String mail, String password, BigDecimal balance, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.balance = balance;
        this.roles = roles;
    }
}
