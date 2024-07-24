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
//@DynamicInsert
public class Users {
    @Id
    private String id; // 아이디

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String email; // 이메일

    @Column(nullable = false)
    private String gender; // 성별

    @Column(nullable = false)
    @ColumnDefault("false")
    private String handicap; // 장애인

    @Column(nullable = false)
    @ColumnDefault("0")
    private int passKey; // 화장실 비번 키

    @ManyToMany
    @JoinTable(name = "userWcFavorites", joinColumns = @JoinColumn(name = "userId"),
    inverseJoinColumns = @JoinColumn(name = "wcId"))
    @ToString.Exclude
    private List<WcInfo> favoriteWcList = new ArrayList<>();

    @OneToMany(mappedBy = "createUser", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<WcInfo> createWcList = new ArrayList<>();

    @OneToMany(mappedBy = "bestUsers", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Best> userBestList = new ArrayList<>();

}
