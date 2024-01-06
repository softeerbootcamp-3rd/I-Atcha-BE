package softee5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import softee5.demo.entity.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("select i.link from Image i where i.history.historyID = :id")
    List<String> findByHistoryId(@Param("id") Long historyId);
}