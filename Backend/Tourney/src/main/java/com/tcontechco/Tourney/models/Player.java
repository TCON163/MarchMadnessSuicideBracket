package com.tcontechco.Tourney.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    @Column(name = "username")
    private String username;

    @NonNull
    @Column(name = "password")

    private String password;

    @NonNull
    @Column
    private String email;





    @OneToOne(mappedBy = "player")
    @JsonIgnore
    private Admin admin;

    @OneToOne(mappedBy = "player")
    @JsonIgnore
    private TPlayer tPlayer;


}
