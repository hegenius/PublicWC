package bitc.fullstack405.publicwc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wcinfo")
@Data
@NoArgsConstructor
public class WcInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 번호

    @Column(nullable = false)
    private int level; // 화장실 등급

    @Column(nullable = false)
    private String name; // 화장실 이름

    @Column(nullable = false)
    private String addr1; // 도로명 주소
    
    @Column(nullable = false)
    private String addr2; // 지번 주소

    @Column(nullable = false)
    private String detailAddr; // 상세 주소

    @Column(nullable = false)
    private String time; // 개방 시간

    @Column(nullable = true)
    private String comment; // 상세글

    @Column(nullable = false)
    private long latitude; // 위도

    @Column(nullable = false)
    private long longitude;  // 경도

    @ManyToOne
    @JoinColumn(name = "favoriteWcList")
    @ToString.Exclude
    private User favoriteUser;

    @ManyToOne
    @JoinColumn(name = "createWcList")
    @ToString.Exclude
    private User createUser;

    @OneToMany(mappedBy = "bestWc", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Best> wcBestList = new ArrayList<>();
}
