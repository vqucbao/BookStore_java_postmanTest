package com.example.demo.payLoad.dto;

import com.example.demo.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class UserDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String dateOfBirth;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String email;
    @NotBlank
    private String gender;

    public UserDTO() {
    }
}