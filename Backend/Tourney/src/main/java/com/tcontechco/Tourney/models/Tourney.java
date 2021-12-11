package com.tcontechco.Tourney.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tourney")
@Getter
@Setter
@NoArgsConstructor
public class Tourney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tourneyId;

    @NonNull
    @Column( name = "title")
    private String title;


    @OneToOne
    @MapsId
    private Player admin;

    @OneToMany(mappedBy = "tourney", targetEntity = TourneyPlayer.class)
    private List<TourneyPlayer> players;

}
