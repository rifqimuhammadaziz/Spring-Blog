package rifqimuhammadaziz.Dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rifqimuhammadaziz.Library.model.PostCategory;
import rifqimuhammadaziz.Library.service.contract.PostCategoryService;

import java.util.List;

@Controller
public class PostCategoryController {

    @Autowired
    private PostCategoryService postCategoryService;

    @GetMapping("/posts/categories")
    public String categories(Model model) {
        List<PostCategory> categories = postCategoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("newCategory", new PostCategory());
        return "posts/categories";
    }

    @PostMapping("/posts/categories/save")
    private String save(@ModelAttribute("newCategory") PostCategory postCategory, RedirectAttributes attributes) {
        try {
            postCategoryService.save(postCategory);
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

    @RequestMapping(value = "/posts/categories/update", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public PostCategory findById(Long id) {
        return postCategoryService.findById(id);
    }

    @GetMapping("/posts/categories/update-category")
    public String update(PostCategory postCategory, RedirectAttributes attributes) {
        try {
            postCategoryService.update(postCategory);
            attributes.addFlashAttribute("updateSuccess", "Category successfully updated");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("updateFailed", "Failed to update category because duplicate name");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("updateFailed", "Error server");
        }
        return "redirect:/posts/categories";
    }

    @RequestMapping(value = "/delete-category", method = {RequestMethod.PUT, RequestMethod.GET})
    public String delete(Long id, RedirectAttributes attributes) {
        try {
            postCategoryService.deleteById(id);
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
            postCategoryService.enableById(id);
            attributes.addFlashAttribute("enableSuccess", "Category successfully enabled");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("enableFailed", "Failed to enable category");
        }
        return "redirect:/posts/categories";
    }

}
