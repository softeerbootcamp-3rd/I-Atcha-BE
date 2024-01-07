package softee5.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feeId;
    private int freeTime;
    private int minuteRate;
    private int addFee;
}
