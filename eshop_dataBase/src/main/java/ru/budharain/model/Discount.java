package ru.budharain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToMany(mappedBy = "discount",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Product> products;
    @Column
    private int discount_amount;
    @Column
    private Date dateStart;
    @Column
    private Date finish;

    public Discount(Long id, int discount_amount, Date dateStart, Date finish) {
        this.id = id;
        this.discount_amount = discount_amount;
        this.dateStart = dateStart;
        this.finish = finish;
    }
}
