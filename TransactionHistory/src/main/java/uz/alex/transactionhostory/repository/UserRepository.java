package uz.alex.transactionhostory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.transactionhostory.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
