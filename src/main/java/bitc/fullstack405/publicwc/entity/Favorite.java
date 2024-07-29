package bitc.fullstack405.publicwc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "favorite")
@Data
@NoArgsConstructor
@AllArgsConstructor
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