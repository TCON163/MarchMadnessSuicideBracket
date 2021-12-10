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
@RequiredArgsConstructor
public class TourneyPlayer {

    @Id
    @Column(name = "tourneyPlayerId")
    private Integer tourneyPlayerId;

    @OneToOne
    @MapsId
    private Player player;

    @OneToOne
    @MapsId
    private Tourney tourney;


    // Currently, using a list to hold the picks of the user, but probably
    // should look into a set. So, that they can't add the same Team twice.
    @ManyToOne
    private List<Picks> picks;

    @Column
    private Boolean alive;


}
