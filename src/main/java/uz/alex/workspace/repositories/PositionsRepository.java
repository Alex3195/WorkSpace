package uz.alex.workspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.workspace.entity.Positions;

public interface PositionsRepository extends JpaRepository<Positions, Integer> {
}
