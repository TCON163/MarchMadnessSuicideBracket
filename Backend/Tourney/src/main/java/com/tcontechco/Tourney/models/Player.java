package com.tcontechco.Tourney.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "player")
@Getter
@Setter
@NoArgsConstructor
public class Player {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer playerId;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "username",unique = true)
    private String username;

    @NonNull
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @NonNull
    @Column
    private String email;


    @OneToMany(mappedBy = "player")
    @JsonIgnore
    private Set<TPlayer> tPlayers = new HashSet<>();

    public Player(Player player){
        this.playerId = player.getPlayerId();
        this.username = player.getUsername();
        this.email = player.getEmail();
        this.firstName = player.getFirstName();
        this.lastName = player.getLastName();
        this.password = "Protected for you";
    }
}
