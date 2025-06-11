package com.trong.bookservice.command.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
<<<<<<< HEAD
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
=======
import lombok.Setter;
>>>>>>> 3ac60f58ee77c519e2afa7ad3ecf50f14e218cd3
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Getter
@Setter
@AllArgsConstructor
<<<<<<< HEAD
@NoArgsConstructor
public class CreateBookCommand {

    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
=======
public class CreateBookCommand {


    @TargetAggregateIdentifier
    private String id;

    private String name;

    private String author;

    private boolean isReady;
>>>>>>> 3ac60f58ee77c519e2afa7ad3ecf50f14e218cd3
}
