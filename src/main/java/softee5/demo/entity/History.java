package softee5.demo.entity;

import jakarta.persistence.*;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
