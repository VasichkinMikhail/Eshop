package ru.budharain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false, name = "order_date")
    private LocalDateTime orderDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItems;
    public Order(Long id, LocalDateTime orderDate, OrderStatus status, User user) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.user = user;
    }
    public enum OrderStatus {
        CREATED, PROCESSED, IN_DELIVERY, DELIVERED, CLOSED, CANCELED
    }
}
