package softee5.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private String runningTime;

    public static Parking createParking(String name, double latitude, double longitude, String fee, String discount, String runtime){
        Parking parking = new Parking();

        parking.name = name;
        parking.latitude = latitude;
        parking.longitude = longitude;
        parking.fee = fee;
        parking.discount = discount;
        parking.runningTime = runtime;

        return parking;
    }
}
