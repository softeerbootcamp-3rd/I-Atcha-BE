package softee5.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingID;
    private String name;
    private double latitude;
    private double longitude;
    @ManyToOne
    @JoinColumn(name = "FEE_ID")
    private Fee fee;
    private String discount;
    private String runningTime;
}
