package com.trong.bookservice.command.model;


<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
=======
import lombok.Getter;
>>>>>>> 3ac60f58ee77c519e2afa7ad3ecf50f14e218cd3
import lombok.Setter;

@Getter
@Setter
<<<<<<< HEAD
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestModel {

    private String id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max=50,  message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Author is mandatory")
    private String author;
    private Boolean isReady;
=======
public class BookRequestModel {
    private String id;
    private String name;
    private String author;
    private boolean isReady;
>>>>>>> 3ac60f58ee77c519e2afa7ad3ecf50f14e218cd3
}
