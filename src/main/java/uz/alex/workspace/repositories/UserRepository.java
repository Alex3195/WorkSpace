package uz.alex.workspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.alex.workspace.entity.Users;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByStatusIsNot(String status);

    @Query(value = "select u from Users u where u.status <> 'DELETED'")
    List<Users> findAllActiveUsers();
}
