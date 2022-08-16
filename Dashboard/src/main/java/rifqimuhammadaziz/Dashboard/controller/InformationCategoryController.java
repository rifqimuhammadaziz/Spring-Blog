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
import rifqimuhammadaziz.Library.model.InformationCategory;
import rifqimuhammadaziz.Library.service.contract.InformationCategoryService;

import java.util.List;

@Controller
@AllArgsConstructor
public class InformationCategoryController {

    private InformationCategoryService categoryService;

    @GetMapping("/informations/categories")
    public String categories(Model model) {
        List<InformationCategory> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("newCategory", new InformationCategory());
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
