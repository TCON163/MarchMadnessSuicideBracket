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
public class Team {
    @Id
    private Integer id;

    @Column
    private String teamName;

    @Column
    private Boolean alive;

    @OneToOne
    @MapsId
    private Picks picks;


}
