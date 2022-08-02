package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.model.PostCategory;

import java.util.List;

public interface PostCategoryService {
    /* ADMIN */
    List<PostCategory> findAll();
    PostCategory save(PostCategory postCategory);
    PostCategory findById(Long id);
    PostCategory update(PostCategory postCategory);
    void deleteById(Long id);
    void enableById(Long id);
    List<PostCategory> findAllByActivated();
}
