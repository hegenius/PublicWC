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
    private boolean gender; // 성별

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean handicap; // 장애인

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isHandicap() {
        return handicap;
    }

    public void setHandicap(boolean handicap) {
        this.handicap = handicap;
    }

    public int getPassKey() {
        return passKey;
    }

    public void setPassKey(int passKey) {
        this.passKey = passKey;
    }

    public List<WcInfo> getFavoriteWcList() {
        return favoriteWcList;
    }

    public void setFavoriteWcList(List<WcInfo> favoriteWcList) {
        this.favoriteWcList = favoriteWcList;
    }

    public List<WcInfo> getCreateWcList() {
        return createWcList;
    }

    public void setCreateWcList(List<WcInfo> createWcList) {
        this.createWcList = createWcList;
    }

    public List<Best> getUserBestList() {
        return userBestList;
    }

    public void setUserBestList(List<Best> userBestList) {
        this.userBestList = userBestList;
    }

}
