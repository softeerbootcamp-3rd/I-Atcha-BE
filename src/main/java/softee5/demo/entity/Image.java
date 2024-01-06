package softee5.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime parkingTime;    // 시작시간
    private String link;

    @ManyToOne
    @JoinColumn(name = "HISTORY_ID")
    private History history;

    @Builder
    public Image(LocalTime parkingTime, String link) {
        this.parkingTime = parkingTime;
        this.link = link;
    }

    public void updateUploadTime(LocalTime updateTime) {
        this.parkingTime = updateTime;
    }
}
