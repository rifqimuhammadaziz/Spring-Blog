package rifqimuhammadaziz.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rifqimuhammadaziz.Library.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
