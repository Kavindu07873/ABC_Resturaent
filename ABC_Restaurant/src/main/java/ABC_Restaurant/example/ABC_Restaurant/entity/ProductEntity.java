package ABC_Restaurant.example.ABC_Restaurant.entity;

import javax.persistence.*;
import lombok.*;

import java.util.List;

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
    @Column(length = 3000)
    private String  mealType;
    @Column(length = 3000)
    private String  description;
    @Column(length = 3000)
    private String image;

    private double price;
    private int quantityInStock;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_Category_id")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<CartEntity> cartEntityList;
}
