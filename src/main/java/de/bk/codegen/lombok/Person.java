package de.bk.codegen.lombok;

import java.util.Date;

import lombok.Data;

@Data
public class Person {

    private String firstname;
    private String lastname;
    private Date birthdate;
}
