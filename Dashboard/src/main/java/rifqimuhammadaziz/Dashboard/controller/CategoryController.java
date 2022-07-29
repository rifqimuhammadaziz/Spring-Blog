package rifqimuhammadaziz.Dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rifqimuhammadaziz.Library.model.Category;
import rifqimuhammadaziz.Library.service.contract.CategoryService;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/posts/categories")
    public String categories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("newCategory", new Category());
        return "categories/categories";
    }

    @PostMapping("/add-category")
    private String add(@ModelAttribute("newCategory") Category category, RedirectAttributes attributes) {
        try {
            categoryService.save(category);
            attributes.addFlashAttribute("createSuccess", "New Category Added!");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("createFailed", "Duplicate category name");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("createFailed", "Error server");
        }
        return "redirect:/posts/categories";
    }

    @RequestMapping(value = "/findById", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Category findById(Long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/update-category")
    public String update(Category category, RedirectAttributes attributes) {
        try {
            categoryService.update(category);
            attributes.addFlashAttribute("updateSuccess", "Category successfully updated");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("updateFailed", "Failed to update category because duplicate name");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("updateFailed", "Error server");
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/delete-category", method = {RequestMethod.PUT, RequestMethod.GET})
    public String delete(Long id, RedirectAttributes attributes) {
        try {
            categoryService.deleteById(id);
            attributes.addFlashAttribute("deleteSuccess", "Category deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("deleteFailed", "Delete category failed");
        }
        return "redirect:/posts/categories";
    }

    @RequestMapping(value = "/enable-category", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enable(Long id, RedirectAttributes attributes) {
        try {
            categoryService.enableById(id);
            attributes.addFlashAttribute("enableSuccess", "Category successfully enabled");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("enableFailed", "Failed to enable category");
        }
        return "redirect:/posts/categories";
    }

}
