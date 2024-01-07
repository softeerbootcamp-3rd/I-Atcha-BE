package softee5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import softee5.demo.entity.History;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("select h from History h where h.member.memberID = :id")
    List<History> findByMemberId(@Param("id") Long memberId);
}
