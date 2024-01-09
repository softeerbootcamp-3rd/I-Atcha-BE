package softee5.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberID;
    private String id;
    private String pwd;
    @Nullable
    private Double latitude;
    @Nullable
    private Double longitude;
    private String parkStartTime;

    public static Member createMember(String id, String pwd, double latitude, double longitude){
        Member member = new Member();

        member.id = id;
        member.pwd = pwd;
        member.latitude = latitude;
        member.longitude = longitude;

        return member;
    }

    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        setUpdateTime();
    }

    public void updateParkingStartTime(String localDateTime) {
        this.parkStartTime = localDateTime;
    }

}
