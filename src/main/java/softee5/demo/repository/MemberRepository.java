package softee5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softee5.demo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
