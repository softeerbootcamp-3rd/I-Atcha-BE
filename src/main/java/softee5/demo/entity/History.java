package softee5.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class History extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyID;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    private String memo;
    @ManyToOne
    @JoinColumn(name = "PARKING_ID")
    private Parking parking;

    private String paidFee;
    private String parkingTime;


    public static History createHistory(Member member, String memo, Parking parking, String paidFee, String parkingTime){
        History history = new History();

        history.member = member;
        history.memo = memo;
        history.parking = parking;
        history.paidFee = paidFee;
        history.parkingTime = parkingTime;

        return history;
    }
}
