package com.tcontechco.Tourney.DTOs;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterPlayerDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
