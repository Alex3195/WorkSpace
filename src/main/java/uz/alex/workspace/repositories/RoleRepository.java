package uz.alex.workspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.workspace.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
}
