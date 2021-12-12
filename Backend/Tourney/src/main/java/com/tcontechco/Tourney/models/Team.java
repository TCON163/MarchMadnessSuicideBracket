package com.tcontechco.Tourney.models;

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

    @OneToMany(mappedBy = "team")
    private List<Picks> pick;


}
