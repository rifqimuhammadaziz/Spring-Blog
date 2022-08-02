package rifqimuhammadaziz.Dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rifqimuhammadaziz.Library.dto.InformationDto;
import rifqimuhammadaziz.Library.model.InformationCategory;
import rifqimuhammadaziz.Library.service.contract.InformationCategoryService;
import rifqimuhammadaziz.Library.service.contract.InformationService;

import java.util.List;

@Controller
public class InformationController {

    @Autowired
    private InformationService informationService;

    @Autowired
    private InformationCategoryService informationCategoryService;

    @GetMapping("/informations")
    public String informations(Model model) {
        List<InformationDto> informations = informationService.findAll();
        model.addAttribute("informations", informations);
        return "informations/informations";
    }

    @GetMapping("/informations/create")
    public String createInformationForm(Model model) {
        List<InformationCategory> categories = informationCategoryService.findAllByActivated();
        model.addAttribute("categories", categories);
        model.addAttribute("information", new InformationDto());
        return "informations/create-info";
    }

    @PostMapping("/informations/save-information")
    public String saveInformation(@ModelAttribute("information") InformationDto informationDto, RedirectAttributes attributes) {
        try {
            informationService.save(informationDto);
            attributes.addFlashAttribute("createSuccess", "New information successully created");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to create new information");
        }
        return "redirect:/informations";
    }
}
