package rifqimuhammadaziz.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rifqimuhammadaziz.Library.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM posts p INNER JOIN postcategories c ON c.category_id = p.category_id WHERE p.category_id = ?1", nativeQuery = true)
    List<Post> getRelatedPost(Long categoryId);

}
