package softee5.demo.entity;

import jakarta.persistence.*;

@Entity
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberID;
    private String id;
    private String pwd;
    private double latitude;
    private double longitude;

    public static Member createMember(String id, String pwd, double latitude, double longitude){
        Member member = new Member();

        member.id = id;
        member.pwd = pwd;
        member.latitude = latitude;
        member.longitude = longitude;

        return member;
    }

}
