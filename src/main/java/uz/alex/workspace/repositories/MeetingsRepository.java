package uz.alex.workspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.alex.workspace.entity.Meetings;

import java.util.List;

public interface MeetingsRepository extends JpaRepository<Meetings, Integer> {
    @Query(value = "select e from Meetings e where e.dataStatus <> 'DELETED' and e.startTime < current_timestamp() and e.endTime< current_timestamp()")
    List<Meetings> findAllActiveMeetings();
}
