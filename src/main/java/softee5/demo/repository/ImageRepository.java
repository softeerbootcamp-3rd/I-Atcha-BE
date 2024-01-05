package softee5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softee5.demo.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
