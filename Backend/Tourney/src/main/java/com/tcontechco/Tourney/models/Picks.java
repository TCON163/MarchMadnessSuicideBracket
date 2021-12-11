package com.tcontechco.Tourney.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Picks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @MapsId
    private Team team;

    @ManyToOne
    @MapsId
    private TourneyPlayer tourneyPlayer;

    @Column
    private Boolean winner;
}
