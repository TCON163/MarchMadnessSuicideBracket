package MarchMadnessProject.Picks.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Player {

    @Id
    @Column(name = "playerId")
    private Integer playerId;

    @NonNull
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "picks")
    @OneToMany(mappedBy = "pickId")
    private List<Picks> picks;

}
