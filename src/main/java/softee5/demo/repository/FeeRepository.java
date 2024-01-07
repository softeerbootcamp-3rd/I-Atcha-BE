package softee5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softee5.demo.entity.Fee;

public interface FeeRepository extends JpaRepository<Fee, Long> {
}
