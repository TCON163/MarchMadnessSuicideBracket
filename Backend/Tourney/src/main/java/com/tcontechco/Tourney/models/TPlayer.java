package com.tcontechco.Tourney.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class TPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tp_id", unique = true)
    private Integer tPId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="tourney_id")
    @JsonIgnore
    private Tourney tourney;


    // Currently, using a list to hold the picks of the user, but probably
    // should look into a set. So, that they can't add the same Team twice.
    @OneToMany(mappedBy = "player")
    private List<Picks> tpPicks = new ArrayList<>();

    @Column
    private Boolean alive;


    @Override
    public boolean equals(Object other){
        if (other == null||other.getClass() != this.getClass()){
            return false;
        }
        final TPlayer compare = (TPlayer) other;

        return (this.getPlayer() == null) ? (compare.getPlayer() == null) : this.getPlayer().getPlayerId().equals(compare.getPlayer().getPlayerId());
    }


}
