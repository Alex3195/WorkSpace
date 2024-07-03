package uz.alex.workspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.workspace.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

    List<Users> findByIsEnabled(Boolean isEnabled);
}
