package softee5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import softee5.demo.entity.Image;

import java.util.Collection;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("select i.link from Image i where i.history.historyID = :id")
    List<String> findLinkByHistoryId(@Param("id") Long historyId);

    @Query("select i from Image i where i.history.historyID = :id")
    List<Image> findImageByHistoryId(@Param("id") Long historyId);
}