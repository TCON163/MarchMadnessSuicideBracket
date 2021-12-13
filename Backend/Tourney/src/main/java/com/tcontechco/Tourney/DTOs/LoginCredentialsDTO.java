package com.tcontechco.Tourney.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginCredentialsDTO {
    private String username;
    private String password;
}
