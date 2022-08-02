package rifqimuhammadaziz.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rifqimuhammadaziz.Library.model.Information;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
}
