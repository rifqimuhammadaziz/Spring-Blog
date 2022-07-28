package rifqimuhammadaziz.Library.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.model.Category;
import rifqimuhammadaziz.Library.repository.CategoryRepository;
import rifqimuhammadaziz.Library.service.contract.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        try {
            Category newCategory = new Category(category.getName());
            return categoryRepository.save(newCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        Category currentCategory = null;
        try {
            currentCategory = categoryRepository.findById(category.getId()).get();
            currentCategory.setName(category.getName());
            currentCategory.setActivated(category.isActivated());
            currentCategory.setDeleted(category.isDeleted());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryRepository.save(currentCategory);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void enableById(Long id) {

    }

    @Override
    public List<Category> findAllByActivated() {
        return categoryRepository.findAllByActivated();
    }
}
