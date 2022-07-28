package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.dto.PostDto;
import rifqimuhammadaziz.Library.model.Post;

import java.util.List;

public interface PostService {
    /* DASHBOARD */
    List<PostDto> findAll();
    Post findById(Long id);
    Post save(PostDto postDto);
    Post update(PostDto postDto);
    void deleteById(Long id);
}
