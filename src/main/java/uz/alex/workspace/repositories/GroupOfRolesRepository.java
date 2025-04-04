package uz.alex.workspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uz.alex.workspace.entity.GroupOfRoles;

public interface GroupOfRolesRepository extends JpaRepository<GroupOfRoles, Integer>, JpaSpecificationExecutor<GroupOfRoles> {
}
