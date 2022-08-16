package rifqimuhammadaziz.Library.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.model.InformationCategory;
import rifqimuhammadaziz.Library.repository.InformationCategoryRepository;
import rifqimuhammadaziz.Library.service.contract.InformationCategoryService;

import java.util.List;

@Service
@AllArgsConstructor
public class InformationCategoryServiceImpl implements InformationCategoryService {

    private InformationCategoryRepository categoryRepository;

    @Override
    public List<InformationCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public InformationCategory save(InformationCategory informationCategory) {
        try {
            InformationCategory category = new InformationCategory(informationCategory.getName());
            return categoryRepository.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public InformationCategory findById(Long id) {
        return null;
    }

    @Override
    public InformationCategory update(InformationCategory informationCategory) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void enableById(Long id) {

    }

    @Override
    public List<InformationCategory> findAllByActivated() {
        return categoryRepository.findAllByActivated();
    }
}
