package rifqimuhammadaziz.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rifqimuhammadaziz.Library.model.InformationCategory;

import java.util.List;

@Repository
public interface InformationCategoryRepository extends JpaRepository<InformationCategory, Long> {

    @Query("SELECT c FROM InformationCategory c WHERE c.isActivated = TRUE AND c.isDeleted = FALSE")
    List<InformationCategory> findAllByActivated();
}
