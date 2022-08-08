package rifqimuhammadaziz.Dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rifqimuhammadaziz.Library.dto.AdminBasicInformation;
import rifqimuhammadaziz.Library.model.PostCategory;
import rifqimuhammadaziz.Library.service.contract.AdminService;
import rifqimuhammadaziz.Library.service.contract.PostCategoryService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class PostCategoryController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PostCategoryService postCategoryService;

    @GetMapping("/posts/categories")
    public String categories(Principal principal, HttpSession session, Model model) {
        if (principal != null) {
            // Get Login Details
            AdminBasicInformation admin = adminService.getLoginDetails(principal.getName());
            model.addAttribute("admin", admin);
            System.out.println(admin);
            session.setAttribute("username", principal.getName());

            // Find All Categories
            List<PostCategory> categories = postCategoryService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("newCategory", new PostCategory());
        } else {
            session.removeAttribute("username");
            return "redirect:/login";
        }
        return "posts/categories";
    }

    @PostMapping("/posts/categories/save")
    private String save(@ModelAttribute("newCategory") PostCategory postCategory, RedirectAttributes attributes) {
        try {
            boolean isCategoryExists = postCategoryService.existByName(postCategory.getName());
            if (isCategoryExists) {
                attributes.addFlashAttribute("createFailed", "Duplicate Category Name.");
            } else {
                postCategoryService.save(postCategory);
                attributes.addFlashAttribute("createSuccess", "New Category Added.");
            }
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("createFailed", "Duplicate Category Name.");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("createFailed", "Server Error.");
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
            attributes.addFlashAttribute("updateSuccess", "Category Successfully Updated.");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("updateFailed", "Failed to update category because duplicate name");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("updateFailed", "Server Error.");
        }
        return "redirect:/posts/categories";
    }

    @RequestMapping(value = "/delete-category", method = {RequestMethod.PUT, RequestMethod.GET})
    public String delete(Long id, RedirectAttributes attributes) {
        try {
            postCategoryService.deleteById(id);
            attributes.addFlashAttribute("deleteSuccess", "Category Deleted Successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("deleteFailed", "Delete Category Failed");
        }
        return "redirect:/posts/categories";
    }

    @RequestMapping(value = "/enable-category", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enable(Long id, RedirectAttributes attributes) {
        try {
            postCategoryService.enableById(id);
            attributes.addFlashAttribute("enableSuccess", "Category Successfully Enabled.");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("enableFailed", "Failed to Enable Category.");
        }
        return "redirect:/posts/categories";
    }

}
