package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.model.InformationCategory;

import java.util.List;

public interface InformationCategoryService {
    /* ADMIN */
    List<InformationCategory> findAll();
    InformationCategory save(InformationCategory informationCategory);
    InformationCategory findById(Long id);
    InformationCategory update(InformationCategory informationCategory);
    void deleteById(Long id);
    void enableById(Long id);
    List<InformationCategory> findAllByActivated();
}
