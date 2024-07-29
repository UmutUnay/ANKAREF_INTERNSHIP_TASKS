package umut.unay.task5_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import umut.unay.task5_spring_boot.entity.EventInfo;
import umut.unay.task5_spring_boot.entity.UserInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>
{
    //List<UserInfo> findAll();
    Optional<UserInfo> findByName(String username);
    Optional<UserInfo> findByEmail(String email);
    Optional<String> findNameByEmail(String email);

    @Query("SELECT u FROM UserInfo u JOIN u.events e WHERE e.id = :id")
    List<EventInfo> getAttendingEvents(int id);
}
