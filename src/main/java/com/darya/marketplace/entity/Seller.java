package com.darya.marketplace.entity;

import com.darya.marketplace.entity.enums.ERole;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "seller")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8, message = "Password should be bigger 8 characters")
    @Column(name = "password")
    private String password;
    @Column(name = "description")
    private String description;

    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "seller_role",
            joinColumns = @JoinColumn(name = "seller_id"))
    private Set<ERole> roles = new HashSet<>();

    @OneToMany(mappedBy = "seller")
    private List<Product> products;
}
