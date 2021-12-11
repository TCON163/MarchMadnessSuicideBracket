package com.tcontechco.Tourney.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
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

    @OneToMany
    private List<TourneyPlayer> players;

}
