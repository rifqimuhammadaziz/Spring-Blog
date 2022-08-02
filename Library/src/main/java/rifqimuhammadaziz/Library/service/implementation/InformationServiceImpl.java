package rifqimuhammadaziz.Library.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.dto.InformationDto;
import rifqimuhammadaziz.Library.model.Information;
import rifqimuhammadaziz.Library.repository.InformationRepository;
import rifqimuhammadaziz.Library.service.contract.InformationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<InformationDto> findAll() {
        List<Information> informations = informationRepository.findAll();
        List<InformationDto> informationDtoList = informations.stream().map(information -> mapperDto(information)).collect(Collectors.toList());

        return informationDtoList;
    }

    @Override
    public InformationDto findById(Long id) {
        return null;
    }

    @Override
    public Information save(InformationDto informationDto) {
        return null;
    }

    @Override
    public Information update(InformationDto informationDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void publishById(Long id) {

    }

    private InformationDto mapperDto(Information information) {
        InformationDto informationDto = modelMapper.map(information, InformationDto.class);
        return informationDto;
    }
}
