package bitc.fullstack405.publicwc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "best")
@Setter
@Getter
@NoArgsConstructor
public class Best {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int good;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @ToString.Exclude
    private Users bestUser;

    @ManyToOne
    @JoinColumn(name = "wcBestList")
    @ToString.Exclude
    private WcInfo bestWC;

}
