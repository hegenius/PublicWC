package bitc.fullstack405.publicwc.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean gender;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean handicap;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int passkey;

    @OneToMany(mappedBy = "favoriteUser", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<WcInfo> favoriteWCList = new ArrayList<>();

    @OneToMany(mappedBy = "createUser", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<WcInfo> createWCList = new ArrayList<>();

    @OneToMany(mappedBy = "bestUser", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Best> userBestList = new ArrayList<>();

}
