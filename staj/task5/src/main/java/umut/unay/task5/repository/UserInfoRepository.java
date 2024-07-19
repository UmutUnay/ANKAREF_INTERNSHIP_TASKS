package umut.unay.task5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umut.unay.task5.entity.UserInfo;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>
{
    Optional<UserInfo> findByName(String username);
    //Optional<UserInfo> findById(int id);
}
