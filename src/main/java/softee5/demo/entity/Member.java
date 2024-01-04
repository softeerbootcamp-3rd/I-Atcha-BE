package softee5.demo.entity;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberID;
    private String id;
    private String pwd;

    private double latitude;
    private double longitude;}
