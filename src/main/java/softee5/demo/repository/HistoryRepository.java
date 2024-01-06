package softee5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softee5.demo.entity.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
