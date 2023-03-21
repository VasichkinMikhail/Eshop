package ru.budharain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private LocalDateTime time;
    @Column(length = 65535, columnDefinition = "LONGTEXT")
    private String text;
    @Column
    private double grade;
    @Column
    @ManyToOne(optional = false)
    private Product product;

    public Comment(Long id, String title, LocalDateTime time, String text, double grade) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.text = text;
        this.grade = grade;
    }
}
