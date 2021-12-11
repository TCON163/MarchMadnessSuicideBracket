package com.tcontechco.Tourney.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tourney")
@Getter
@Setter
@NoArgsConstructor
public class Tourney implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  tourneyId;


    @Column(name = "tourney_title")
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    private Admin headGuy;



    @OneToMany(mappedBy = "tourney")
    private Set<TPlayer> players = new HashSet<>();

    public void enrollTPlayer(TPlayer tp){
        players.add(tp);
    }

}
