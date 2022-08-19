package rifqimuhammadaziz.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rifqimuhammadaziz.Library.model.Information;

import java.util.List;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {

    /* BLOG */
    @Query("SELECT i FROM Information i WHERE i.isDeleted = FALSE ORDER BY i.createdDate ASC")
    List<Information> getAllInformation();

    @Query(value = "SELECT * FROM informations i WHERE i.is_deleted = FALSE ORDER BY i.created_date DESC LIMIT 5", nativeQuery = true)
    List<Information> getRecentlyInformation();

    @Query(
            value = "SELECT * FROM informations i " +
                    "INNER JOIN infocategories c " +
                    "ON c.id = i.information_category_id " +
                    "WHERE i.information_category_id = ?1 AND i.is_deleted = FALSE",
            nativeQuery = true
    )
    List<Information> getAllInformationByCategory(Long categoryId);

    @Query(
            value = "SELECT * FROM informations i " +
                    "INNER JOIN infocategories c " +
                    "ON c.id = i.information_category_id " +
                    "WHERE i.information_category_id = ?1 AND i.is_deleted = FALSE",
            nativeQuery = true
    )
    List<Information> getRelatedAllInformation(Long categoryId);
}
