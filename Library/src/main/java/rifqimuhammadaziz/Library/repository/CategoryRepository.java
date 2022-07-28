package rifqimuhammadaziz.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rifqimuhammadaziz.Library.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.isActivated = TRUE AND c.isDeleted = FALSE")
    List<Category> findAllByActivated();
}
