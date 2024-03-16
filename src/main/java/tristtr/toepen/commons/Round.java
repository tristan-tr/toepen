package tristtr.toepen.commons;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Round implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<Player, Integer> points;

    @ManyToOne
    private Game game;

    // maybe we don't need this? the player with the lowest points wins?
    // although that might be messed up by penalty points
    @ManyToOne
    private Player winner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "round")
    private List<Trick> tricks; // 4 tricks in a round

    @ElementCollection
    private List<Card> deck;

    // TODO: list of who toeped?
}
