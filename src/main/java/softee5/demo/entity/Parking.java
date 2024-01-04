package softee5.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parkingID;
    private String name;
    private double lat;
    private double lon;
    private String fee;
    private String discount;
    private String runtime;
}
