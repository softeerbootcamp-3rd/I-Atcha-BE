package softee5.demo.entity;

import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageID;
    private int parkingTime;
    private String link;

    @ManyToOne
    @JoinColumn(name = "HISTORY_ID")
    private History history;
}
