package softee5.demo.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softee5.demo.entity.Memo;

@Repository
@Transactional
public interface MemoRepository extends JpaRepository<Memo, Long> {

}
