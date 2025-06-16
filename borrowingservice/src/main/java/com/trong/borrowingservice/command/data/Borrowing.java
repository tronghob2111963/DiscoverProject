package com.trong.borrowingservice.command.data;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;


@Entity
@Table(name =  "borrowings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrowing {
    @Id
    private String id;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;
}
