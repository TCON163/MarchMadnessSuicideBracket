package com.tcontechco.Tourney.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tourneyPlayer")
@Getter
@Setter
@NoArgsConstructor
public class TourneyPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tourneyPlayerId;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name="tourney_id", nullable = false)
    private Tourney tourney;


    // Currently, using a list to hold the picks of the user, but probably
    // should look into a set. So, that they can't add the same Team twice.
    @OneToMany(mappedBy = "tourneyPlayer", targetEntity = Picks.class)
    private List<Picks> tpPicks;

    @Column
    private Boolean alive;


}
