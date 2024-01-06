package softee5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softee5.demo.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findById(String id);
}
