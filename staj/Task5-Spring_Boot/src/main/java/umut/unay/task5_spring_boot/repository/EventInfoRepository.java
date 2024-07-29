package umut.unay.task5_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import umut.unay.task5_spring_boot.entity.EventInfo;
import umut.unay.task5_spring_boot.entity.UserInfo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventInfoRepository extends JpaRepository<EventInfo, Integer> {
    // Get
    /*@Query("SELECT e FROM EventInfo e")
    List<EventInfo> findAll();*/

    EventInfo findById(long id);
    Optional<EventInfo> findByName(String name);

    @Query("SELECT e FROM EventInfo e JOIN e.attendingUsers u WHERE u.id = :userId")
    List<EventInfo> userSpecificEvents(long userId);

    @Query("SELECT e FROM EventInfo e WHERE e.categoryId = :categoryId")
    List<EventInfo> categorySpecificEvents(int categoryId);

    @Query("SELECT u FROM EventInfo e JOIN e.attendingUsers u WHERE e.id = :eventId")
    List<UserInfo> getAttendees(long eventId);

    @Query("SELECT e FROM EventInfo e WHERE e.date BETWEEN :startDate AND :endDate")
    List<EventInfo> findEventsInBetween(Date startDate, Date endDate);
}
