package ABC_Restaurant.example.ABC_Restaurant.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 255)
    private String  mealName;
    @Column(length = 255, nullable = false, unique = true)
    private String  mealType;
    private String  description;
}
