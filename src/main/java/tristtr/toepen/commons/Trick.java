package tristtr.toepen.commons;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Trick implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Card> cardsPlayed;

    @ManyToOne
    private Round round;

    @OneToOne
    private Player turn;

    @OneToOne
    private Player winner;
}
