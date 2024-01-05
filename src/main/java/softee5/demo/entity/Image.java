package softee5.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long imageID;
    private int parkingTime;
    private String link;

    @ManyToOne
    @JoinColumn(name = "HISTORY_ID")
    private History history;
}
