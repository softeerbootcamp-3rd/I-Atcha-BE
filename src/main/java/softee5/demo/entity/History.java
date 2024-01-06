package softee5.demo.entity;

import jakarta.persistence.*;

@Entity
public class History extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyID;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @OneToOne
    @JoinColumn(name = "MEMO_ID")
    private Memo memo;
    @OneToOne
    @JoinColumn(name = "PARKING_ID")
    private Parking parking;

    private String paidFee;
    private String parkingTime;


    public static History createHistory(Member member,Memo memo, Parking parking, String paidFee, String parkingTime){
        History history = new History();

        history.member = member;
        history.memo = memo;
        history.parking = parking;
        history.paidFee = paidFee;
        history.parkingTime = parkingTime;

        return history;
    }
}
