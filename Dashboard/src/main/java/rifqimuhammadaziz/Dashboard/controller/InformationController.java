package rifqimuhammadaziz.Dashboard.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rifqimuhammadaziz.Library.dto.AdminBasicInformation;
import rifqimuhammadaziz.Library.dto.InformationDto;
import rifqimuhammadaziz.Library.dto.PostDto;
import rifqimuhammadaziz.Library.model.InformationCategory;
import rifqimuhammadaziz.Library.service.contract.AdminService;
import rifqimuhammadaziz.Library.service.contract.InformationCategoryService;
import rifqimuhammadaziz.Library.service.contract.InformationService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class InformationController {

    private InformationService informationService;
    private InformationCategoryService informationCategoryService;
    private AdminService adminService;

    @GetMapping("/informations")
    public String informations(Model model, Principal principal, HttpSession session) {
        if (principal != null) {
            // Get Login Details
            AdminBasicInformation admin = adminService.getLoginDetails(principal.getName());
            model.addAttribute("admin", admin);
            session.setAttribute("username", principal.getName());

            // Find All Informations
            List<InformationDto> informations = informationService.findAll();
            model.addAttribute("informations", informations);
        } else {
            session.removeAttribute("username");
            return "redirect:/login";
        }
        return "informations/informations";
    }

    @GetMapping("/informations/create")
    public String createInformationForm(Model model, Principal principal, HttpSession session) {
        if (principal != null) {
            // Get Login Details
            AdminBasicInformation admin = adminService.getLoginDetails(principal.getName());
            model.addAttribute("admin", admin);
            session.setAttribute("username", principal.getName());

            // Create Information
            List<InformationCategory> categories = informationCategoryService.findAllByActivated();
            model.addAttribute("categories", categories);
            model.addAttribute("information", new InformationDto());
        } else {
            session.removeAttribute("username");
            return "redirect:/login";
        }
        return "informations/create-info";
    }

    @PostMapping("/informations/save-information")
    public String saveInformation(@ModelAttribute("information") InformationDto informationDto,
                                  RedirectAttributes attributes,
                                  Principal principal) {
        try {
            informationService.save(informationDto, principal);
            attributes.addFlashAttribute("createSuccess", "New information successfully created");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to create new information");
        }
        return "redirect:/informations";
    }

    @GetMapping("/informations/update/{id}")
    public String updateInformationForm(@PathVariable("id") Long id, Model model, Principal principal, HttpSession session) {
        if (principal != null) {
            // Get Login Details
            AdminBasicInformation admin = adminService.getLoginDetails(principal.getName());
            model.addAttribute("admin", admin);
            session.setAttribute("username", principal.getName());

            // Update Information
            List<InformationCategory> categories = informationCategoryService.findAllByActivated();
            InformationDto informationDto = informationService.findById(id);
            model.addAttribute("categories", categories);
            model.addAttribute("information", informationDto);
        } else {
            session.removeAttribute("username");
            return "redirect:/login";
        }
        return "/informations/update-info";
    }

    @PostMapping("/informations/update/{id}")
    public String updateInformation(@PathVariable("id") Long id,
                                    @ModelAttribute("information") InformationDto informationDto,
                                    RedirectAttributes attributes) {
        try {
            informationService.update(informationDto);
            attributes.addFlashAttribute("updateSuccess", "Current Information successfully updated");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to update existing information");
        }
        return "redirect:/informations";
    }

    @GetMapping("/informations/preview/{id}")
    public String preview(@PathVariable("id") Long id, Model model, Principal principal, HttpSession session) {
        if (principal != null) {
            // Get Login Details
            AdminBasicInformation admin = adminService.getLoginDetails(principal.getName());
            model.addAttribute("admin", admin);
            session.setAttribute("username", principal.getName());

            // Update Information
            InformationDto informationDto = informationService.findById(id);
            model.addAttribute("information", informationDto);
        } else {
            session.removeAttribute("username");
            return "redirect:/login";
        }
        return "/informations/preview";
    }
}
