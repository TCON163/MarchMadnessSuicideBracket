package com.tcontechco.Tourney.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tcontechco.Tourney.utils.SqlTimeDeserializer;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor(onConstructor=@__({@Autowired}))
@NoArgsConstructor(onConstructor=@__({@Autowired}))
public class Game {

    @Id
    @Column(name="gameId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameId;

    @Column(name="date")
    @NonNull
    private Date date;

    @JsonFormat(pattern = "HH:mm")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column(name = "game_time")
    @NonNull
    private Time time;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_id", referencedColumnName = "team_id")
    private Team home;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "away_id", referencedColumnName = "team_id")
    private Team away;

    @Column(name="complete")
    @NonNull
    private boolean completed;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id", referencedColumnName = "team_id")
    private Team winner;



}
