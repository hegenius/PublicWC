package bitc.fullstack405.publicwc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.DateTimeException;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class WCInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String Addr1;

    @Column(nullable = false)
    private String Addr2;

    @Column(nullable = false)
    private LocalDateTime time;


    @ManyToOne
    @JoinColumn(name = "User_favorite")
    @ToString.Exclude
    private User provider;
}
