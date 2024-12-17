package br.edu.utfpr.users.services;

import br.edu.utfpr.users.dtos.UserRequestDTO;
import br.edu.utfpr.users.dtos.UserResponseDTO;
import br.edu.utfpr.users.models.User;
import br.edu.utfpr.users.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setTel(userRequest.tel());
        user.setToken(generateToken());

        User savedUser = userRepository.save(user);
        return mapToResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapToResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> searchUsers(String email, String token) {
        List<User> users;
        if (email != null) {
            users = userRepository.findByEmail(email);
        } else if (token != null) {
            users = userRepository.findByToken(token);
        } else {
            users = userRepository.findAll();
        }
        return users.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setTel(userRequest.tel());

        User updatedUser = userRepository.save(user);
        return mapToResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }

    private String generateToken() {
        return "token_" + System.currentTimeMillis(); // Simples geração de token
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setTel(user.getTel());
        dto.setToken(user.getToken());
        return dto;
    }
}
