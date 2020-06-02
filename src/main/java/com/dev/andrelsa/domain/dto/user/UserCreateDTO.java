package com.dev.andrelsa.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserCreateDTO {

    @NotEmpty(message = "name is required")
    private String name;

    @Email
    @NotNull
    @NotEmpty(message = "email is required")
    private String email;

    @NotNull
    @NotEmpty(message = "password is required")
    @Size(min = 6, max = 255)
    private String password;

    @NotEmpty(message = " dateBirth is required")
    private LocalDate dateBirth;

}
