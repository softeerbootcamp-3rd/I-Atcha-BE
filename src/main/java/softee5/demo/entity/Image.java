package softee5.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

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

    public void changeUploadTime(LocalTime updateTime) {
        this.parkingTime = updateTime;
    }

    public void changeLink(String link) {
        this.link = link;
    }

    public void setHistory(History history){
        this.history = history;
    }
}
