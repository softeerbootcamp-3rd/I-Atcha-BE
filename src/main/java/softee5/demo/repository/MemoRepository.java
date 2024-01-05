package softee5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softee5.demo.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
