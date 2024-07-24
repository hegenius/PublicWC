package bitc.fullstack405.publicwc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// 이 클래스가 JPA 엔티티임을 나타냄
@Entity
public class User {
    // id 필드는 자동으로 생성되는 기본 키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    // 사용자 이름 저장 필드
    private String username;
    // 비밀번호 저장 필드
    private String password;
    // 이메일 저장 필드
    private String email;
    // 성별 저장 필드
    private String gender;
    // 장애 여부 저장 필드
    private boolean isDisabled; // 장애 여부
    

    // 사용자 ID를 반환, 설정하는 메서드
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // 사용자 이름을 반환, 설정하는 메소드
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // 비밀번호를 반환, 설정하는 메소드
    public String getPassword() {
        return password;
    }
    // 비밀번호를 설정하는 메서드
    public void setPassword(String password) {
        this.password = password; // 비밀번호 설정 (암호화 필요)
    }

    // 성별을 반환, 설정하는 메서드
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender; // 성별 정보 설정
    }
    
    // 이메일을 반환, 설정하는 메서드
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    // 장애 여부를 반환, 설정하는 메서드
    public boolean isDisabled() {
        return isDisabled;
    }
    public void setDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }
}