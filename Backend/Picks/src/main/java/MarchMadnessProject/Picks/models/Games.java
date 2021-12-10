package MarchMadnessProject.Picks.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor(onConstructor=@__({@Autowired}))
@NoArgsConstructor(onConstructor=@__({@Autowired}))
public class Games{

    @Id
    @Column(name="gameId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameId;

    @Column(name="date")
    @NonNull
    private Date date;

    @Column(name="home_team")
    @NonNull
    private String home;

    @Column(name="away_team")
    @NonNull
    private String away;

    @Column(name="complete")
    @NonNull
    private boolean completed;

    @Column(name="game_winner")
    private String winner;

    @Column(name="picks")
    @JsonIgnore
    @OneToMany(mappedBy = "pickId")
    private List<Picks> picks;
}