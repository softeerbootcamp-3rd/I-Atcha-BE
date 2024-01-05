package softee5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import softee5.demo.entity.Parking;

@Repository
@Transactional
public interface ParkingRepository extends JpaRepository<Parking, Long> {
}
