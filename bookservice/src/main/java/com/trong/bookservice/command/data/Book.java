package com.trong.bookservice.command.data;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
<<<<<<< HEAD
import lombok.NoArgsConstructor;
=======
>>>>>>> 3ac60f58ee77c519e2afa7ad3ecf50f14e218cd3
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
<<<<<<< HEAD
@NoArgsConstructor
public class Book {
    @Id
    private String id;
    private String name;
    private String author;
    private Boolean isReady;


=======
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String author;
    private boolean isReady;
>>>>>>> 3ac60f58ee77c519e2afa7ad3ecf50f14e218cd3
}
