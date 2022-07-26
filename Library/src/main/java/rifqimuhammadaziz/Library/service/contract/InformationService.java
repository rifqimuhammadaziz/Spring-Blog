package rifqimuhammadaziz.Library.service.contract;

import org.springframework.data.domain.Page;
import rifqimuhammadaziz.Library.dto.InformationDto;
import rifqimuhammadaziz.Library.model.Information;

import java.security.Principal;
import java.util.List;

public interface InformationService {
    /* DASHBOARD */
    List<InformationDto> findAll();
    InformationDto findById(Long id);
    Information save(InformationDto informationDto, Principal principal);
    Information update(InformationDto informationDto);
    void deleteById(Long id);

    /* BLOG */
    Page<Information> pageAllInformation(int pageNo);
    List<Information> getAllInformation();
    List<Information> getRecentlyInformation();
    List<Information> getAllInformationByCategory(Long categoryId);
    List<Information> getRelatedAllInformation(Long categoryId);
}
