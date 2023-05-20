package com.darya.forSeller.entity;

import com.darya.forSeller.entity.enums.ERole;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    //@NotEmpty(message = "Name should not be empty")
    @Column(name = "name")
    private String name;
    //@NotEmpty(message = "Password should not be empty")
    //@Size(min = 8, message = "Password should be bigger 8 characters")
    @Column(name = "password")
    private String password;
    @Column(name = "money")
    private double money;

    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "client_role",
            joinColumns = @JoinColumn(name = "client_id"))
    private Set<ERole> roles = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private List<Basket> baskets;
}
