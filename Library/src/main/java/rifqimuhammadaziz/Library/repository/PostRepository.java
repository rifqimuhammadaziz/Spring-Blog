package rifqimuhammadaziz.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rifqimuhammadaziz.Library.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /* BLOG */
    @Query("SELECT p FROM Post p WHERE p.isPublished = TRUE AND p.isDeleted = FALSE ORDER BY p.createdDate ASC")
    List<Post> getAllPosts();

    @Query(value = "SELECT * FROM posts p INNER JOIN postcategories c ON c.id = p.post_category_id WHERE p.post_category_id = ?1 ORDER BY rand() ASC LIMIT 5", nativeQuery = true)
    List<Post> getRelatedPost(Long categoryId);

}
