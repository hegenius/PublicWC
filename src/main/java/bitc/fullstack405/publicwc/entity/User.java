package bitc.fullstack405.publicwc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String handicap;

    @Column(nullable = false)
    private int key;

    @OneToMany(mappedBy = "favorite", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<WCInfo> productList = new ArrayList<>();

}
