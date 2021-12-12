package com.tcontechco.Tourney.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pick")
@Getter
@Setter
@NoArgsConstructor
public class Picks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pick_id", unique = true)
    private Integer pickId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pick_id", referencedColumnName = "teamId")
    private Team pick;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tPId")
    private TPlayer player;

    @Column
    private Boolean winner;
}
