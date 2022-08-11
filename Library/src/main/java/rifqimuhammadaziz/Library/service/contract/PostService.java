package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.dto.PostDto;
import rifqimuhammadaziz.Library.model.Post;

import java.security.Principal;
import java.util.List;

public interface PostService {
    /* DASHBOARD */
    List<PostDto> findAll();
    PostDto findById(Long id);
    Post save(PostDto postDto, Principal principal);
    Post update(PostDto postDto);
    void deleteById(Long id);
    void publishById(Long id);

    /* BLOG */
    List<Post> getPosts();
    List<Post> getPostsByCategory(Long categoryId);
    List<Post> getRelatedPosts(Long categoryId);
}
