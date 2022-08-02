package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.dto.InformationDto;
import rifqimuhammadaziz.Library.model.Information;

import java.util.List;

public interface InformationService {
    /* DASHBOARD */
    List<InformationDto> findAll();
    InformationDto findById(Long id);
    Information save(InformationDto informationDto);
    Information update(InformationDto informationDto);
    void deleteById(Long id);
    void publishById(Long id);
}
