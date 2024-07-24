package umut.unay.task5_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umut.unay.task5_spring_boot.entity.CategoryInfo;

@Repository
public interface CategoryInfoRepository extends JpaRepository<CategoryInfo, Long>
{
}
