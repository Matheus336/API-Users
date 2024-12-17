package br.edu.utfpr.users.repositories;

import br.edu.utfpr.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
    List<User> findByToken(String token);
}
