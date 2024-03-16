package tristtr.toepen.commons;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL) // players can be in multiple games
    private List<Player> players;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game") // rounds can only be in one game
    private List<Round> rounds; // score can be calculated from rounds
}
