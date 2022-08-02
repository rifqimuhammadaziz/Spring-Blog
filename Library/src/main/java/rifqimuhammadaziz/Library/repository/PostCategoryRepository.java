package rifqimuhammadaziz.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rifqimuhammadaziz.Library.model.PostCategory;

import java.util.List;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {

    @Query("SELECT c FROM PostCategory c WHERE c.isActivated = TRUE AND c.isDeleted = FALSE")
    List<PostCategory> findAllByActivated();
}
