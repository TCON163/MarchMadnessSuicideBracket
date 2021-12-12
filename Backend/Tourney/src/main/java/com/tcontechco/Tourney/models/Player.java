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
    @Column(name = "username")
    private String username;

    @NonNull
    @Column(name = "password")

    private String password;

    @NonNull
    @Column
    private String email;





//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "admin_id")
//    @JsonIgnore
//    private Admin admin;

    @OneToMany(mappedBy = "player")
    @JsonIgnore
    private Set<TPlayer> tPlayers = new HashSet<>();


}
