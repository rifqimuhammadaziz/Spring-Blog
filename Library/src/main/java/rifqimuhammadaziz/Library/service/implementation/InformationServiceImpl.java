package rifqimuhammadaziz.Library.service.implementation;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.dto.InformationDto;
import rifqimuhammadaziz.Library.model.Admin;
import rifqimuhammadaziz.Library.model.Information;
import rifqimuhammadaziz.Library.repository.AdminRepository;
import rifqimuhammadaziz.Library.repository.InformationRepository;
import rifqimuhammadaziz.Library.service.contract.InformationService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InformationServiceImpl implements InformationService {

    private AdminRepository adminRepository;
    private InformationRepository informationRepository;
    private ModelMapper modelMapper;

    @Override
    public List<InformationDto> findAll() {
        List<Information> informations = informationRepository.findAll();
        List<InformationDto> informationDtoList = informations.stream().map(information -> mapperDto(information)).collect(Collectors.toList());

        return informationDtoList;
    }

    @Override
    public InformationDto findById(Long id) {
        Information information = informationRepository.findById(id).get();
        InformationDto informationDto = mapperDto(information);
        return informationDto;
    }

    @Override
    public Information save(InformationDto informationDto, Principal principal) {
        try {
            Admin admin = adminRepository.findByUsername(principal.getName());
            Information information = mapperEntity(informationDto);
            information.setAuthor(admin.getFullName());
            information.setCreatedDate(LocalDateTime.now());
            information.setDeleted(false);
            System.out.println(information);
            return informationRepository.save(information);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Information update(InformationDto informationDto) {
        try {
            Information currentInformation = informationRepository.findById(informationDto.getId()).get();
            currentInformation.setTitle(informationDto.getTitle());
            currentInformation.setInformationCategory(informationDto.getInformationCategory());
            currentInformation.setContent(informationDto.getContent());
            return informationRepository.save(currentInformation);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {

    }

    /*
    BLOG
     */

    @Override
    public Page<Information> pageAllInformation(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        List<Information> information = informationRepository.findAll();
        Page<Information> informationPages = toPage(information, pageable);
        return informationPages;
    }

    @Override
    public List<Information> getAllInformation() {
        return informationRepository.getAllInformation();
    }

    @Override
    public List<Information> getRecentlyInformation() {
        return informationRepository.getRecentlyInformation();
    }

    @Override
    public List<Information> getAllInformationByCategory(Long categoryId) {
        return informationRepository.getAllInformationByCategory(categoryId);
    }

    @Override
    public List<Information> getRelatedAllInformation(Long categoryId) {
        return informationRepository.getRelatedAllInformation(categoryId);
    }

    private Page toPage(List<Information> information, Pageable pageable) {
        if (pageable.getOffset() >= information.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > information.size())
                ? information.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());
        List list = information.subList(startIndex, endIndex);
        return new PageImpl(list, pageable, information.size());
    }

    private InformationDto mapperDto(Information information) {
        InformationDto informationDto = modelMapper.map(information, InformationDto.class);
        return informationDto;
    }

    private Information mapperEntity(InformationDto informationDto) {
        Information information = modelMapper.map(informationDto, Information.class);
        return information;
    }
}
