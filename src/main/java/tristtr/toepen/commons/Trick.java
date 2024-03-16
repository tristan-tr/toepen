package tristtr.toepen.commons;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Trick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Card> cardsPlayed;

    @ManyToOne
    private Round round;

    @OneToOne
    private Player turn;

    @OneToOne
    private Player winner;
}
