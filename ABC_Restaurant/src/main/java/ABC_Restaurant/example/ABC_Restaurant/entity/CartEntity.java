package ABC_Restaurant.example.ABC_Restaurant.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int quantity;
    private double totalPrice;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_User_id")
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_Product_id")
    private ProductEntity productEntity;

}

