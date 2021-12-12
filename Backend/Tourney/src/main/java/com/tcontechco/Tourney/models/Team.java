package com.tcontechco.Tourney.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToOne(mappedBy = "pick")
    @JsonIgnore
    private Picks pick;

    @OneToOne(mappedBy = "home")
    @JsonIgnore
    private Game gameHome;

    @OneToOne(mappedBy = "away")
    @JsonIgnore
    private Game gameAway;

    @OneToOne(mappedBy = "winner")
    @JsonIgnore
    private Game gameWinner;


}
