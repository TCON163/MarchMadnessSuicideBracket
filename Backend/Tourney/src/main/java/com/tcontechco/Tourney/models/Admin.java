package com.tcontechco.Tourney.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
public class Admin {
    @Id
    @Column(name = "admin_id")
    private Integer adminId;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Player player;


    @OneToMany(mappedBy = "headGuy")
    @JsonIgnore
    private Set<Tourney> tourney = new HashSet<>();
}
