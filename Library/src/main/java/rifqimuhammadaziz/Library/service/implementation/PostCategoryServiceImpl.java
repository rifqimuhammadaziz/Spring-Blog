package rifqimuhammadaziz.Library.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.model.PostCategory;
import rifqimuhammadaziz.Library.repository.PostCategoryRepository;
import rifqimuhammadaziz.Library.service.contract.PostCategoryService;

import java.util.List;

@Service
@AllArgsConstructor
public class PostCategoryServiceImpl implements PostCategoryService {

    private PostCategoryRepository postCategoryRepository;

    @Override
    public List<PostCategory> findAll() {
        return postCategoryRepository.findAll();
    }

    @Override
    public PostCategory save(PostCategory postCategory) {
        try {
            PostCategory newPostCategory = new PostCategory(postCategory.getName());
            newPostCategory.setActivated(true);
            return postCategoryRepository.save(newPostCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PostCategory findById(Long id) {
        return postCategoryRepository.findById(id).get();
    }

    @Override
    public PostCategory update(PostCategory postCategory) {
        PostCategory currentPostCategory = null;
        try {
            currentPostCategory = postCategoryRepository.findById(postCategory.getId()).get();
            currentPostCategory.setName(postCategory.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postCategoryRepository.save(currentPostCategory);
    }

    @Override
    public void deleteById(Long id) {
        PostCategory postCategory = postCategoryRepository.findById(id).get();
        postCategory.setDeleted(true);
        postCategory.setActivated(false);
        postCategoryRepository.save(postCategory);
    }

    @Override
    public void enableById(Long id) {
        PostCategory postCategory = postCategoryRepository.findById(id).get();
        postCategory.setDeleted(false);
        postCategory.setActivated(true);
        postCategoryRepository.save(postCategory);
    }

    @Override
    public List<PostCategory> findAllByActivated() {
        return postCategoryRepository.findAllByActivated();
    }

    @Override
    public boolean existByName(String name) {
        return postCategoryRepository.existsByName(name);
    }
}
