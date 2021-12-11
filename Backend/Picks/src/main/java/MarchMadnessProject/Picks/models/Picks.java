package MarchMadnessProject.Picks.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor(onConstructor=@__({@Autowired}))
@NoArgsConstructor(onConstructor=@__({@Autowired}))
public class Picks {

    @Id
    @Column(name="pickId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pickId;

    @Column(name="player")
    @ManyToOne
    @JoinColumn(columnDefinition = "playerId")
    private Player player;

    @Column(name = "game")
    @ManyToOne
    @JoinColumn(columnDefinition = "gameId")
    private Games game;

    @Column(name = "selection")
    private String teamPicked;

    @Column(name="resolved")
    private boolean resolved;

    @Column(name = "correct")
    private boolean correct;
}
