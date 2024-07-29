package bitc.fullstack405.publicwc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "favorite")
@Setter
@Getter
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private Users favoriteUsers;

    @ManyToOne
    @JoinColumn(name = "wcId")
    @ToString.Exclude
    private WcInfo favoriteWc;

}