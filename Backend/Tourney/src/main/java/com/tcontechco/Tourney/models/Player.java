package com.tcontechco.Tourney.models;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
@Getter
@Setter
@NoArgsConstructor
public class Player {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "username", unique = true)
    private String username;

    @NonNull
    @Column(name = "password")
    private String password;

    @NonNull
    @Column
    private String email;

    @OneToOne
    private Tourney tourney;

    @OneToMany(mappedBy = "player")
    private List<TourneyPlayer> tp;


}
