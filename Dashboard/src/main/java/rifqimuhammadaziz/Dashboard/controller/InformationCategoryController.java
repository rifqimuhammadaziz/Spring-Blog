package rifqimuhammadaziz.Dashboard.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rifqimuhammadaziz.Library.dto.AdminBasicInformation;
import rifqimuhammadaziz.Library.dto.InformationDto;
import rifqimuhammadaziz.Library.model.InformationCategory;
import rifqimuhammadaziz.Library.service.contract.AdminService;
import rifqimuhammadaziz.Library.service.contract.InformationCategoryService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class InformationCategoryController {

    private InformationCategoryService categoryService;
    private AdminService adminService;

    @GetMapping("/informations/categories")
    public String categories(Model model, Principal principal, HttpSession session) {
        if (principal != null) {
            // Get Login Details
            AdminBasicInformation admin = adminService.getLoginDetails(principal.getName());
            model.addAttribute("admin", admin);
            session.setAttribute("username", principal.getName());

            // Update Information
            List<InformationCategory> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("newCategory", new InformationCategory());
        } else {
            session.removeAttribute("username");
            return "redirect:/login";
        }
        return "informations/categories";
    }

    @PostMapping("/informations/categories/create")
    public String create(@ModelAttribute("newCategory") InformationCategory category, RedirectAttributes attributes) {
        try {
            categoryService.save(category);
            attributes.addFlashAttribute("createSuccess", "New Category added");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("createFailed", "Duplicate category name");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("createFailed", "Error server");
        }
        return "redirect:/informations/categories";
    }
}
