package softee5.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long memoID;
    private String content;

    public static Memo createMemo(String content){
        Memo memo = new Memo();

        memo.content = content;

        return  memo;
    }

}
