package ru.budharain.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime time;
    @Column(length = 65535, columnDefinition = "LONGTEXT")
    private String text;

    private double grade;
}
