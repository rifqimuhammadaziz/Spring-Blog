package rifqimuhammadaziz.Blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rifqimuhammadaziz.Library.dto.InformationDto;
import rifqimuhammadaziz.Library.model.Information;
import rifqimuhammadaziz.Library.model.InformationCategory;
import rifqimuhammadaziz.Library.service.contract.InformationCategoryService;
import rifqimuhammadaziz.Library.service.contract.InformationService;

import java.util.List;

@Controller
@AllArgsConstructor
public class InformationController {

    private InformationService informationService;
    private InformationCategoryService informationCategoryService;

//    @GetMapping("/informations")
//    public String informations(Model model) {
//        // Get All Information
//        List<InformationDto> informationList = informationService.findAll();
//        model.addAttribute("informations", informationList);
//
//        // Get All Information Categories
//        List<InformationCategory> informationCategories = informationCategoryService.findAll();
//        model.addAttribute("informationCategories", informationCategories);
//
//        return "informations/informations";
//    }

    @GetMapping("/informations/{pageNo}")
    public String informationPages(@PathVariable("pageNo") int pageNo,
                                   Model model) {
        Page<Information> informationPage = informationService.pageAllInformation(pageNo);
        model.addAttribute("size", informationPage.getSize());
        model.addAttribute("totalPages", informationPage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("informations", informationPage);

        return "informations/informations";
    }

    @GetMapping("/information/{id}")
    public String information(@PathVariable("id") Long id, Model model) {
        // Get Information By ID
        InformationDto informationDto = informationService.findById(id);
        model.addAttribute("information", informationDto);

        // Get Related Information
        Long categoryId = informationDto.getInformationCategory().getId();
        List<Information> information = informationService.getRelatedAllInformation(categoryId);
        model.addAttribute("informations", information);

        return "informations/information-detail";
    }

}