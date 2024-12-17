package br.edu.utfpr.users.services;

import br.edu.utfpr.users.dtos.UserRequestDTO;
import br.edu.utfpr.users.dtos.UserResponseDTO;
import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequest);
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> searchUsers(String email, String token);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequest);
    void deleteUser(Long id);
}
