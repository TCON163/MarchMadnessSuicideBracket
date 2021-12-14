package com.tcontechco.Tourney.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="admin_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Admin headGuy;



    @OneToMany(mappedBy = "tourney")
    private Set<TPlayer> players = new HashSet<>();

    public void enrollTPlayer(TPlayer tp){
        players.add(tp);
    }

}
