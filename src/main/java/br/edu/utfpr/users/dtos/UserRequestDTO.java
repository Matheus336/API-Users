package br.edu.utfpr.users.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter

public record UserRequestDTO(
        String name,
        String email,
        String password,
        String tel
) {}
