package com.tcontechco.Tourney.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Team {
    @Id
    @Column(name = "team_id")
    private Integer teamId;

    @Column
    private String teamName;

    @Column
    private Boolean alive;

    @OneToMany(mappedBy = "pick")
    @JsonIgnore
    private List<Picks> pick = new ArrayList<>();

    @OneToMany(mappedBy = "home")
    @JsonIgnore
    private List<Game> gameHome = new ArrayList<>();

    @OneToMany(mappedBy = "away")
    @JsonIgnore
    private List<Game> gameAway = new ArrayList<>();

    @OneToMany(mappedBy = "winner")
    @JsonIgnore
    private List<Game> winners = new ArrayList<>();


}
