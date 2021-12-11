package com.tcontechco.Tourney.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id",updatable = false, insertable = false)
    private Player player;

    @JsonIgnore
    @OneToOne(mappedBy = "headGuy")
    private Tourney tourney;
}
