package softee5.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memoID;
    private String content;
}
