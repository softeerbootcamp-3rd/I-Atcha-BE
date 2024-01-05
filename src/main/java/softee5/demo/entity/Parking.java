package softee5.demo.entity;

import jakarta.persistence.*;

@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingID;
    private String name;
    private double latitude;
    private double longitude;
    private String fee;
    private String discount;
    private String running_time;
}
